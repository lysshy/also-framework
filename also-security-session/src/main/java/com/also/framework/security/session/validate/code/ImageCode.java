package com.also.framework.security.session.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class ImageCode implements Serializable {

    private static final long serialVersionUID = -7831615057416168810L;

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
