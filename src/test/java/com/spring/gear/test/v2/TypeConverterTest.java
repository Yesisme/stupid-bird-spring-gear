package com.spring.gear.test.v2;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.spring.gear.beans.SimpleTypeCoverter;
import com.spring.gear.beans.TypeConverter;
import com.spring.gear.beans.TypeMismatchException;




/**
 * Created by xiang.wei on 2018/6/30
 *
 * @author xiang.wei
 */
public class TypeConverterTest {
    @Test
    public void testConvertStringToInt() {
        TypeConverter typeConverter = new SimpleTypeCoverter();
        Integer i = typeConverter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

        try {
            typeConverter.convertIfNecessary("3.1", Integer.class);
        } catch (TypeMismatchException e) {
            return;
        }

        fail();
    }
    @Test
    public void testConvertStringToBoolean() {
        TypeConverter converter = new SimpleTypeCoverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true, b.booleanValue());

        try {
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        } catch (TypeMismatchException e) {
            return;
        }
        fail();

    }
}
