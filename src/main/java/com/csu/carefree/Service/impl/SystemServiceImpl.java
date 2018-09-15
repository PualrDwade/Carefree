package com.csu.carefree.Service.impl;

import com.csu.carefree.Service.SystemService;

public class SystemServiceImpl implements SystemService {
    @Override
    public boolean sendEmail(String content, String code, String type) {
        return false;
    }

    @Override
    public String getRandomCode() {
        return null;
    }
}
