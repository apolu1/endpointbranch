package main.test;

import com.example.controller.BaseController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class BaseControllerTest {

    @InjectMocks
    BaseController baseController = new BaseController();

    @Test
    public void testAdd(){
        String expected = baseController.getList().toString();
        System.out.println(expected);
       // Assert.assertEquals(baseController.getList(),"[{\"description\":\"Cloud Foundry sponsored by Pivotal\",\"api_version\":\"2.34\"},{\"description\":\"Cloud Foundry sponsored by Pivotal2\",\"api_version\":\"2.34\"}]");
        Assert.assertEquals(expected,"[EndpointInfo{description='Cloud Foundry sponsored by Pivotal', api_version='2.34'}, EndpointInfo{description='Cloud Foundry sponsored by Pivotal2', api_version='2.34'}]");

    }

}