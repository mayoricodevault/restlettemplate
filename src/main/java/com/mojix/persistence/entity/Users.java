package com.mojix.persistence.entity;

import java.util.Date;

/**
 * Created by carolasilvateran on 7/8/15.
 */
public class Users {

    private int id;
    private long fbid;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private Date dob;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getFbid() {
        return fbid;
    }

    public void setFbid(long fbid) {
        this.fbid = fbid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

//        `avatar` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'user',
//        `country` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `city` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `state` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `about_me` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `blogurl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `fb_link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `tw_link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `is_featured` int(11) DEFAULT NULL,
//        `permission` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `confirmed` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `ip_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `socialsecurity` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `answer1` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `answer2` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `answer3` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `answer4` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
//        `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
//        `deleted_at` timestamp NULL DEFAULT NULL,
//        `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `fb_link_url` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `remember_token` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `notify` tinyint(1) DEFAULT NULL,
//        `subscription_id` int(11) DEFAULT '0',
//        `subscription_date` datetime DEFAULT NULL,
//        `daysLeft` int(11) DEFAULT '0',
//        `accessType` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
//        `original_password` varchar(120) COLLATE utf8_unicode_ci DEFAULT '',
//        `reset_token` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;