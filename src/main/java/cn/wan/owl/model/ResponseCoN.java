package cn.wan.owl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCoN {
    private String msg;

    private ResponseEntity <InputStreamResource>  responseEntity;
    private int code;

    public static ResponseCoN success(ResponseEntity responseEntity) {
        return new ResponseCoN("success", responseEntity, 0);
    }

}
