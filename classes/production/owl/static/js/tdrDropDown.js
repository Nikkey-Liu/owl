;
(function ($) {
    'use strict';
    var settings = { //设置的初始值
        selectTitle: '标题'
        , regReplace: {"value": "select的值的字段", "name": "select的text的字段"}
        , submitValue: "value"//提交时候input的name值
        , selectInfo: {//当前select的value值
            value: []
            , text: []
        }
        , searchable: true  //需要查找模糊查询功能
        , ismultiterm: true //是否多选
        , searchNoData: '<li style="color:#ddd">查无数据</li>'
        , ajax: {}
        , ajaxTextInit: {} //ajax的输入值保存
        , pageIndex: 1
        , keyword: ""
    };

    var Dropdown = function (options, el) {
//	var type = function(obj){ //检测基本类型
//		return Object.prototype.toString.call(obj);
//	}
        var textHandle = function (info) {
            return (info instanceof Array) ? info : "" == "[object Array]" ? info : String(info).split(",");
        };
        this.$el = $(el);
        this.config = options;
        this.input = '<input type="text" maxLength="20" placeholder="请输入' + this.config.selectTitle + '">';
        this.textArray = textHandle(this.config.selectInfo.text);
        this.valueArray = textHandle(this.config.selectInfo.value);
        this.selectTitle = "请选择" + this.config.selectTitle;
        this.init();
        this.$placeholder.data("value", this.valueArray); //给select赋值
        this.$placeholder.text(this.textArray);
        this.$submitInput.val(this.valueArray);

    }

    Dropdown.prototype = {
        init: function () {
            this.$el.append(this.templateOption());
            this.ajaxInit();
            this.setAppendDom();
            this.bindEvent();
        },
        templateOption: function () { //html模版
            var searchable = this.config.searchable;
            var templateSearch = searchable ? '<span class="dropdown-search">' + this.input + '</span>' : '';
            return '<a href="javascript:;" class="dropdown-display">'
                + '<div class="submit-input"> <input type="text" name="' + this.config.submitValue + '"  style="display:none;"/> </div>'
                + '<span class="dropdown-chose-list">'
                + '<span data-value="" class="placeholder">' + this.selectTitle + '</span>'
                + '</span>'
                + '</a>'
                + '<a href="javascript:;" class="dropdown-clear-all">×</a>'
                + '<div class="dropdown-main">' + templateSearch
                + '<ul></ul>'
                + '<div class="pagebottom">共<span class="pageCount"></span>页'
                + '<span class="dropdown-pagepre"></span>'
                + '<span class="pageIndex">1</span><span class="dropdown-pagenext"></span></div></div>';
        },
        setAppendDom: function () { //获取所需要的,后添加的dom
            var $el = this.$el;
            this.$clear = $el.find("a.dropdown-clear-all");
            this.$placeholder = $el.find("span.placeholder");
            this.$input = $el.find(".dropdown-search input");
            this.$submitInput = $el.find(".submit-input input");
        },
        bindEvent: function () {	//绑定事件
            var self = this, $el = this.$el, $input = $el.find('.dropdown-search input');
            $(document).click(function () { //点击其他部位隐藏下拉列表
                $el.removeClass('active');
            });
            $el.on('click', function (e) {
                e.stopPropagation(); //阻止冒泡
            }).on('click', 'span.placeholder', function (e) {
                $el.addClass('active');
                $input.focus();
            }).on('keyup click', '.dropdown-search input', this.Bind(function (e) { //输入数据后操作
                this.config.pageIndex = 1;
                this.config.keyword = $input.val();
                this.ajaxInit();
            })).on('click', '.dropdown-pagenext', this.Bind(function (e) { //下一页
                if (this._tdrAjaxdata.TotalPage > this.config.pageIndex) { //总页数大于当前页码
                    this.config.pageIndex++;
                    this.ajaxInit();
                }
            })).on('click', '.dropdown-pagepre', this.Bind(function (e) { //上一页
                if (this.config.pageIndex > 1) {
                    this.config.pageIndex--;
                    this.ajaxInit();
                }
            }));

            this.$clear.on("click", function () {
                self.clearSelect();
            });
        },
        ajaxInit: function () { //调用ajax方法获取数据
            var thisDrop = this;
            /*ajax参数配置*/
            var self = thisDrop.config, obj = self.ajax["data"] ? self.ajax["data"] : {};
            for (var item in obj) {
                if (self[obj[item]] || self[obj[item]] === "") { //如果data中的key的值在pagelist里面有对应的值
                    self['ajaxTextInit'][item] = obj[item];  //把对应的page:_current等存在  self['_pageInit']
                }
                if (self['ajaxTextInit'][item]) {
                    obj[item] = self[self['ajaxTextInit'][item]]; //获取pageInit中item对应的值
                }
            }
            if (self.ajax["complete"]) {
                var a = self.ajax["complete"];
                var b = thisDrop.Bind(thisDrop.ajaxReturnUse);
                self.ajax["complete"] = function () {
                    a();
                    b();
                }
            } else {
                self.ajax["complete"] = thisDrop.Bind(thisDrop.ajaxReturnUse);
            }
            /*ajax参数配置 end*/
            $.ajax(thisDrop.config.ajax);

        },
        ajaxReturnUse: function () {//对ajax返回的数据进行处理
            var self = this;
            /*ajax返回参数配置    正则替换*/
            this.dataInit();
            var dataArray = self._tdrAjaxdata.Data ? self._tdrAjaxdata.Data : [];
            /*ajax返回参数配置 end*/
            /*获取返回数据后,对界面html配置*/
            this.dataProcessing(dataArray); //插入html
            this.initDropdownChose(dataArray);
            /*获取返回数据后,对界面html配置 end*/
            self.pageConfig(); //分页配置

        },
        dataInit: function () {
            //把原来数组中数据处key替换成固定的value,name   dataArray = [{value:2,name:"艾玛"}];
            var self = this, regReplace = this.config.regReplace;
            Object.keys(regReplace).forEach(function (item, index) {
                var reg = new RegExp(regReplace[item], "ig");
                self._tdrAjaxdata = JSON.parse(JSON.stringify(self._tdrAjaxdata).replace(reg, item));
            });
        },
        dataProcessing: function (data) {//获取到ajax数据以后 ,將数据拼接到html
            var $el = this.$el;
            var self = this;
            //清空div
            $el.find(".dropdown-main ul").empty();
            data = data ? data : {};
            if (!data || JSON.stringify(data) == "{}") { //没有数据的时候提示
                $el.find(".dropdown-main ul").append(this.config.searchNoData);
            } else {
                for (var i = 0; i < data.length; i++) {
                    $el.find(".dropdown-main ul").append('<li data-value="' + data[i].value + '" class="dropdown-option ">' + data[i].name + '</li>');
                }
                this.liCliFc();
            }
        },
        liCliFc: function () {  	//点击列表触发的时间
            var self = this
                , $el = self.$el
                , $input = self.$input
                , mult = self.config.ismultiterm;
            /*循环列表进行事件绑定*/
            $el.find("li.dropdown-option").each(function () {
                var $this = $(this)
                $this.on('click', function (event) {
                    var $target = $(event.target);
                    $el.find("option").removeAttr('selected'); //清空原来选中
                    var text = $(this).text(), value = $(this).attr("data-value");

                    if ($this[0].className.indexOf("dropdown-chose") == -1) {//添加选中
                        if (!mult) {
                            $el.find("li").removeClass("dropdown-chose"),
                                $el.removeClass('active');//隐藏下拉框
                        }
                        $this.addClass("dropdown-chose");

                        if (!mult) {//单选
                            self.textArray[0] = text;
                            self.valueArray[0] = value;
                        } else {//多选
                            self.textArray.push(text);
                            self.valueArray.push(value);
                        }
                        $input.val(text);
                    } else { //取消选中
                        mult && ($this.removeClass("dropdown-chose"));
                        self.textArray.splice($.inArray(String(text), self.textArray), 1);
                        self.valueArray.splice($.inArray(String(value), self.valueArray), 1);
                    }
                    self.$placeholder.text(self.textArray[0] ? self.textArray.toString() : self.selectTitle);
                    self.$submitInput.val(self.valueArray);
                    self.$placeholder.data("value", self.valueArray);
                });
            });
        },
        initDropdownChose: function (data) { // 重置列表选中状态,和选中数据显示
            var valueArray = this.valueArray, $li = this.$el.find("li");
            this.$placeholder.text(this.textArray[0] ? this.textArray.toString() : this.selectTitle);
            $li.each(function () {
                if (valueArray.indexOf(String($(this).data("value"))) != -1) {
                    $(this).addClass("dropdown-chose");
                }
            });
        },
        pageConfig: function () {//分页部分控制
            var _tdrAjaxdata = this._tdrAjaxdata;
            _tdrAjaxdata.PageSize = this.config.ajax["data"].pagesize
                , _tdrAjaxdata.TotalPage = Math.ceil(_tdrAjaxdata.TotalItem / _tdrAjaxdata.PageSize); //共有多少页,计算并保存

            if (_tdrAjaxdata.TotalItem > _tdrAjaxdata.PageSize) { //所有数小于当前页数时候,分页隐藏
                this.$el.find(".pagebottom").show();
            } else {
                this.$el.find(".pagebottom").hide();
            }
            this.$el.find("span.pageIndex").text(this.config.pageIndex);
            this.$el.find("span.pageCount").text(_tdrAjaxdata.TotalPage);

        },
        getSelectValue: function () { //获取当前选中的值value
            return this.$el.find("span.placeholder").data("value");
        },
        getSelectText: function () { //获取当前选中的值的text
            return this.$el.find("span.placeholder").text();
        },
        clearSelect: function () {
            this.$placeholder.text(this.selectTitle).data("value", null);
            this.$submitInput.val(null);
            this.$input.val(null);
            this.textArray = [];
            this.valueArray = [];
            this.$el.find("li").removeClass("dropdown-chose");
        },
        Bind: function (handler, obj) {//通过bind改变对象的上下文结构
            if (obj == undefined) obj = this;
            if (handler.bind) {
                return handler.bind(obj);
            } else {
                var self = this, boundArgs = arguments;
                return function () {
                    var args = [], i;
                    for (i = 2; i < boundArgs.length; i++) {
                        args.push(boundArgsp[i]);
                    }
                    for (i = 0; i < arguments.length; i++) {
                        args.push(arguments[i]);
                    }
                    return handler.apply(obj, args);
                }
            }
        }
    };

    $.fn.dropdown = function (options) {
        this.each(function (index, el) {
            $(el).data('dropdown', new Dropdown($.extend(true, {}, settings, options), el)); //给当前dom添加drapdown参数,new Dropdown方法,用options对setting进行深层替换
        });
        return this;
    }
})(jQuery);