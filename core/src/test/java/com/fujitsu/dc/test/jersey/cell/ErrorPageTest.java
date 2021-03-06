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
package com.fujitsu.dc.test.jersey.cell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import com.fujitsu.dc.core.DcCoreException;
import com.fujitsu.dc.core.DcCoreMessageUtils;
import com.fujitsu.dc.test.categories.Integration;
import com.fujitsu.dc.test.categories.Regression;
import com.fujitsu.dc.test.categories.Unit;
import com.fujitsu.dc.test.jersey.DaoException;
import com.fujitsu.dc.test.jersey.DcException;
import com.fujitsu.dc.test.jersey.DcResponse;
import com.fujitsu.dc.test.jersey.DcRestAdapter;
import com.fujitsu.dc.test.jersey.DcRunner;
import com.fujitsu.dc.test.setup.Setup;
import com.fujitsu.dc.test.unit.core.UrlUtils;
import com.sun.jersey.test.framework.JerseyTest;

/**
 * Test for Error Page.
 */
@RunWith(DcRunner.class)
@Category({Unit.class, Integration.class, Regression.class })
public class ErrorPageTest extends JerseyTest {

    /**
     * constructor.
     */
    public ErrorPageTest() {
        super("com.fujitsu.dc.core.rs");
    }

    /**
     * エラーページへのGETで指定したコードに対応するメッセージが返却されること.
     */
    @Test
    public final void エラーページへのGETで指定したコードに対応するメッセージが返却されること() {

        String code = DcCoreException.OData.JSON_PARSE_ERROR.getCode();
        DcResponse res = requesttoErrorPage(code);

        assertEquals(HttpStatus.SC_OK, res.getStatusCode());

        // レスポンスヘッダのチェック
        assertEquals(MediaType.TEXT_HTML + ";charset=UTF-8", res.getFirstHeader(HttpHeaders.CONTENT_TYPE));

        // レスポンスボディのチェック
        checkResponseBody(res, code);

    }

    /**
     * 定義されていないコードを指定してエラーページを取得しundefinedとなること.
     */
    @Test
    public final void personiumで定義されていないコードを指定してエラーページを取得しundefinedとなること() {

        String code = "dummyCode";
        DcResponse res = requesttoErrorPage(code);

        assertEquals(HttpStatus.SC_OK, res.getStatusCode());

        // レスポンスヘッダのチェック
        assertEquals(MediaType.TEXT_HTML + ";charset=UTF-8", res.getFirstHeader(HttpHeaders.CONTENT_TYPE));

        // レスポンスボディのチェック
        checkResponseBody(res, null);

    }

    /**
     * コードの値を指定せずにエラーページを取得しundefinedとなること.
     */
    @Test
    public final void コードの値を指定せずにエラーページを取得しundefinedとなること() {

        String code = "";
        DcResponse res = requesttoErrorPage(code);

        assertEquals(HttpStatus.SC_OK, res.getStatusCode());

        // レスポンスヘッダのチェック
        assertEquals(MediaType.TEXT_HTML + ";charset=UTF-8", res.getFirstHeader(HttpHeaders.CONTENT_TYPE));

        // レスポンスボディのチェック
        checkResponseBody(res, null);
    }

    /**
     * コードを指定せずにエラーページを取得しundefinedとなること.
     */
    @Test
    public final void コードを指定せずにエラーページを取得しundefinedとなること() {

        DcRestAdapter rest = new DcRestAdapter();
        DcResponse res = null;

        // リクエストヘッダをセット
        HashMap<String, String> requestheaders = new HashMap<String, String>();

        try {
            res = rest.getAcceptEncodingGzip(
                    UrlUtils.cellRoot(Setup.TEST_CELL1) + "__html/error", requestheaders);
        } catch (DcException e) {
            e.printStackTrace();
        }

        assertEquals(HttpStatus.SC_OK, res.getStatusCode());

        // レスポンスヘッダのチェック
        assertEquals(MediaType.TEXT_HTML + ";charset=UTF-8", res.getFirstHeader(HttpHeaders.CONTENT_TYPE));

        // レスポンスボディのチェック
        checkResponseBody(res, null);

    }

    /**
     * エラーページへのPOSTで405となること.
     */
    @Test
    public final void エラーページへのPOSTで405となること() {

        String code = DcCoreException.OData.JSON_PARSE_ERROR.getCode();
        DcRestAdapter rest = new DcRestAdapter();
        DcResponse res = null;

        // リクエストヘッダをセット
        HashMap<String, String> requestheaders = new HashMap<String, String>();

        try {
            res = rest.post(UrlUtils.cellRoot(Setup.TEST_CELL1) + "__html/error?code=" + code, "", requestheaders);
        } catch (DcException e) {
            e.printStackTrace();
        }
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, res.getStatusCode());
    }

    /**
     * エラーページへのPUTで405となること.
     */
    @Test
    public final void エラーページへのPUTで405となること() {

        String code = DcCoreException.OData.JSON_PARSE_ERROR.getCode();
        DcRestAdapter rest = new DcRestAdapter();
        DcResponse res = null;

        // リクエストヘッダをセット
        HashMap<String, String> requestheaders = new HashMap<String, String>();

        try {
            res = rest.put(UrlUtils.cellRoot(Setup.TEST_CELL1) + "__html/error?code=" + code, "", requestheaders);
        } catch (DcException e) {
            e.printStackTrace();
        }
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, res.getStatusCode());
    }

    /**
     * エラーページへのDELETEで405となること.
     */
    @Test
    public final void エラーページへのDELETEで405となること() {

        String code = DcCoreException.OData.JSON_PARSE_ERROR.getCode();
        DcRestAdapter rest = new DcRestAdapter();
        DcResponse res = null;

        // リクエストヘッダをセット
        HashMap<String, String> requestheaders = new HashMap<String, String>();

        try {
            res = rest.del(UrlUtils.cellRoot(Setup.TEST_CELL1) + "__html/error?code=" + code, requestheaders);
        } catch (DcException e) {
            e.printStackTrace();
        }
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, res.getStatusCode());
    }

    /**
     * エラーページにリクエストを投入する.
     * @return レスポンス
     */
    private DcResponse requesttoErrorPage(String code) {
        DcRestAdapter rest = new DcRestAdapter();
        DcResponse res = null;

        // リクエストヘッダをセット
        HashMap<String, String> requestheaders = new HashMap<String, String>();

        try {
            res = rest.getAcceptEncodingGzip(UrlUtils.cellRoot(Setup.TEST_CELL1) + "__html/error?code="
                    + code, requestheaders);
        } catch (DcException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * レスポンスボディのチェックを行う.
     * @param res レスポンス情報
     * @param expectedCode 期待するエラーコード
     */
    public static void checkResponseBody(DcResponse res, String expectedCode) {
        String body = null;
        String expectedMessage = null;
        String expectedTitle = DcCoreMessageUtils.getMessage("PS-ER-0001");
        if (expectedCode == null) {
            expectedMessage = DcCoreMessageUtils.getMessage("PS-ER-0002");
        } else {
            expectedMessage = DcCoreMessageUtils.getMessage(expectedCode);
        }
        try {
            body = res.bodyAsString();
            System.out.println(body);
            assertEquals(
                    "<html><head><title>" + expectedTitle + "</title></head><body><h1>" + expectedTitle + "</h1><p>"
                            + expectedMessage + "</p></body></html>",
                    body.replaceFirst("<!-- .*-->", ""));
        } catch (DaoException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
}
