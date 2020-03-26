package com.fkzm.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//由于定义了全局异常处理器,内部异常,404默认也都回去 error页面,这不是应该有的效果,404应该去404页面,于是需要定义一个404异常,并标注 @ResponseStatus
//处理器通过 AnnotationUtils判断,如果是@ResponseStatus, 则将这个异常抛出,由 springboot 进行默认处理,转到404
//这有点像小孩子想长大,大人给了自由,结果闯了祸不知所措了还得大人来收拾烂摊子
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends  RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
