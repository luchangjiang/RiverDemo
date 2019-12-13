package com.river.powermock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-12-13 21:49
 **/
@Service
public class MockServiceImpl implements MockService {
    @Autowired
    private MockMapper mockMapper;

    @Override
    public int count(MockModel model){
        return mockMapper.count(model);
    }

    @Override
    public MockModel getModel(String name){
        return mockMapper.getModel(name);
    }

    @Override
    public boolean makeFile(String path) {
        File file = new File(path);
        return file.exists();
    }
}
