package com.integration.create.abstractfactory.impl;

import com.integration.create.abstractfactory.Button;
import com.integration.create.abstractfactory.ComboBox;
import com.integration.create.abstractfactory.SkinFactory;
import com.integration.create.abstractfactory.TextField;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//Spring皮肤工厂：具体工厂
public class SpringSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SpringButton();
    }

    public TextField createTextField() {
        return new SpringTextField();
    }

    public ComboBox createComboBox() {
        return new SpringComboBox();
    }

}
