/*
 * Copyright (C) 2020 The zfoo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package com.zfoo.protocol.serializer.typescript;

import com.zfoo.protocol.model.Triple;
import com.zfoo.protocol.registration.field.IFieldRegistration;

import java.lang.reflect.Field;

/**
 * @author godotg
 * @version 3.0
 */
public interface ITsSerializer {

    /**
     * 获取属性的类型，名称，默认值
     */
    Triple<String, String, String> field(Field field, IFieldRegistration fieldRegistration);

    void writeObject(StringBuilder builder, String objectStr, int deep, Field field, IFieldRegistration fieldRegistration);

    String readObject(StringBuilder builder, int deep, Field field, IFieldRegistration fieldRegistration);

}