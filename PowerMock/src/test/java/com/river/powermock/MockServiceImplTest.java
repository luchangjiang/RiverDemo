package com.river.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PrepareForTest({MockUtil.class, MockServiceImpl.class}) // 所有需要测试的类列在此处，适用于模拟final类或有final, private, static, native方法的类
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
public class MockServiceImplTest {
    // 将@Mock注解的示例注入进来
    @InjectMocks
    private MockServiceImpl mockService;
    @Mock
    private MockMapper mockMapper;

    /**
     * mock普通方法
     */
    @Test
    public void testSelectAppAdvertisementList() {
        MockModel model = new MockModel();
        PowerMockito.when(mockMapper.count(model)).thenReturn(2);

        Assert.assertEquals(2, mockService.count(model));
    }

    @Test
    public void testStaticMethod(){
        PowerMockito.mockStatic(MockUtil.class);
        PowerMockito.when(MockUtil.nextInt(10)).thenReturn(7);
        Assert.assertEquals(7, MockUtil.nextInt(10));
    }

    @Test
    public void testNewObject() throws Exception {
        File file = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments("abc").thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(mockService.makeFile("abc"));
    }
}