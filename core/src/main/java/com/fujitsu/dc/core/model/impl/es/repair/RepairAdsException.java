/**
 * personium.io
 * Copyright 2014 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fujitsu.dc.core.model.impl.es.repair;

/**
 * ADSへのデータ補正時にスローする例外.
 */
public class RepairAdsException extends Exception {

    /**
     * デフォルトシリアルバージョンID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ.
     * @param msg メッセージ
     * @param cause 原因となったThrowable
     */
    public RepairAdsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * コンストラクタ.
     * @param msg メッセージ
     */
    public RepairAdsException(String msg) {
        super(msg);
    }

}