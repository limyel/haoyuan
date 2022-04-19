package com.limyel.haoyuan.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "openid", length = 50)
    private String openid;

    @Column(name = "nickname", length = 60)
    private String nickname;

    @Column(name = "unify_uid")
    private Long unifyUid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile", length = 30)
    private String mobile;

    @Lob
    @Column(name = "wx_profile")
    private String wxProfile;

}