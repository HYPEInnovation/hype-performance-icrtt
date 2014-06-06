package de.hype.performance.icrtt

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._

object ScenarioReportIL6MC {
    val scn = scenario("Report: IL6 *MC*")
        .exec(http("login")
            .get("/trust")
            .headers(Headers.headers_get)
        )
        .exec(http("login")
            .get("/trust")
            .headers(Headers.headers_get)
            .queryParam( """username""", """CIC_Vehicle""")
        )
        .exec(http("load start page")
            .get("/servlet/hype/IMT")
            .headers(Headers.headers_get)
            .queryParam( """userAction""", """BrowseCurrentUser""")
            .queryParam( """templateName""", """MenuItem""")
            .check(regex( """var csrfToken = '(.+?)'""").saveAs("csrfToken"))
            .check(regex( """var uniqueLong = '(\d+)'""").saveAs("uniqueLong"))
        )
        .exec(http("load search page")
            .get("/servlet/hype/IMT")
            .headers(Headers.headers_get)
            .queryParam( """userAction""", """BrowseCurrentUser""")
            .queryParam( """templateName""", """MenuItem""")
            .queryParam( """rkId""", """13262871""")
            .check(regex( """select_idea_statuses.*""").transform(s => """actionSelectDocumentsExplorer\(\d+, '([0-9a-f\-]+)'""".r.findFirstMatchIn(s).get.group(1)).saveAs("dialogParamsId"))
            .check(regex( """select_idea_statuses.*""").transform(s => """actionSelectDocumentsExplorer\(\d+, '[0-9a-f\-]+', '([\d\,]+)'""".r.findFirstMatchIn(s).get.group(1)).saveAs("templateContext"))
        )
        .exec(http("initial search (on page load)")
            .post("/servlet/hype/IMT")
            .headers(Headers.headers_post)
            .param("""userAction""", """InvokeStatic""")
            .param("""qualifiedClassName""", """hype.apps.imt.ImtUtilProxy""")
            .param("""methodName""", """performIdeaPoolSearch""")
            .param("""argsAsStrings""", """{"id":13262272,"menuItemId":"13262871","status":"maximized","searchFields":[{"status":"maximized","visible":true,"content":{"id":"13263863","selectedDocuments":[]}},{"status":"maximized","visible":true,"content":{"id":"13266415","value":""}},{"status":"maximized","visible":true,"content":{"id":"13321386","selectedDocuments":[]}},{"status":"maximized","visible":true,"content":{"id":"13263980","selectedDocuments":[]}},{"status":"maximized","visible":true,"content":{"id":"13282711","value":""}},{"status":"maximized","visible":true,"content":{"id":"14260275","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"14260281","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"13278773","value":""}},{"status":"maximized","visible":true,"content":{"id":"13266421","value":""}},{"status":"maximized","visible":false,"content":{"id":"13263869","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263925","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263931","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263937","value":""}},{"status":"maximized","visible":false,"content":{"id":"13263943","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263949","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263986","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263992","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13316729","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263998","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264029","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264035","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264041","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264047","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264078","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264084","value":""}},{"status":"minimized","visible":false,"content":{"id":"13279637","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13313409","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313415","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313421","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313452","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13331612","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13331618","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"14260287","selectedDocuments":[]}}]}""")
            .param("""csrfToken""", "${csrfToken}")
        )
        //NOTE: I didn't have the saved search "CIC-vehicle1". I used "IL3 Newer" instead, so you may want to adapt the parameters of this call
        .exec(http("load saved search")
            .post("/servlet/hype/IMT")
            .headers(Headers.headers_post)
            .param("""userAction""", """InvokeStatic""")
            .param("""qualifiedClassName""", """hype.apps.imt.ImtUtilProxy""")
            .param("""methodName""", """loadSavedIdeaPoolSearch""")
            .multiValuedParam("""argsAsStrings""", List("13302777", "13262272"))
            .param("""csrfToken""", "${csrfToken}")
        )
        .exec(http("saved search (after selection)")
            .post("/servlet/hype/IMT")
            .headers(Headers.headers_post)
            .param("""userAction""", """InvokeStatic""")
            .param("""qualifiedClassName""", """hype.apps.imt.ImtUtilProxy""")
            .param("""methodName""", """performIdeaPoolSearch""")
            .param("""argsAsStrings""", """{"id":13262272,"menuItemId":"13262871","status":"maximized","searchFields":[{"status":"maximized","visible":true,"content":{"id":"13263863","selectedDocuments":[{"id":"13221390","displayName":"IL3","iconUrl":"/apps/IMT/Html/images/Ideenstatus.gif?v=1392818823"}]}},{"status":"maximized","visible":true,"content":{"id":"13266415","value":"cic-0*"}},{"status":"maximized","visible":true,"content":{"id":"13321386","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263980","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13282711","value":""}},{"status":"maximized","visible":true,"content":{"id":"14260275","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"14260281","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"13278773","value":""}},{"status":"maximized","visible":true,"content":{"id":"13266421","value":""}},{"status":"maximized","visible":true,"content":{"id":"13263869","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263925","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263931","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263937","value":""}},{"status":"maximized","visible":false,"content":{"id":"13263943","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263949","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263986","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263992","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13316729","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263998","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264029","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264035","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264041","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264047","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264078","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264084","value":""}},{"status":"minimized","visible":false,"content":{"id":"13279637","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13313409","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313415","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313421","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313452","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13331612","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13331618","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"14260287","selectedDocuments":[]}}]}""")
            .param("""csrfToken""", "${csrfToken}")
        )
        .exec(http("select status IL6")
            .get("/servlet/hype/IMT")
            .headers(Headers.headers_get)
            .queryParam("""treeId""", "Tree${uniqueLong}")
            .queryParam("""expandSelected""", """true""")
            .queryParam("""thresholdToHideTree""", """-1""")
            .queryParam("""templateContext""", "${templateContext}")
            .queryParam("""selDocs""", """""")
            .queryParam("""ldapSearch""", """true""")
            .queryParam("""expandDepth""", """0""")
            .queryParam("""isMltpl""", """true""")
            .queryParam("""titleOrEmptyString""", """""")
            .queryParam("""userAction""", """DocumentExplorerDialog""")
            .queryParam("""disableUnlink""", """false""")
            .queryParam("""docId""", """13263863""")
            .queryParam("""dialogParamsId""", "${dialogParamsId}")
        )
        .exec(http("perform search")
            .post("/servlet/hype/IMT")
            .headers(Headers.headers_post)
            .param("""userAction""", """InvokeStatic""")
            .param("""qualifiedClassName""", """hype.apps.imt.ImtUtilProxy""")
            .param("""methodName""", """performIdeaPoolSearch""")
            .param("""argsAsStrings""", """{"id":13262272,"menuItemId":"13262871","status":"maximized","searchFields":[{"status":"maximized","visible":true,"content":{"id":"13263863","selectedDocuments":[{"id":"13221463","displayName":"IL6","iconUrl":"/apps/IMT/Html/images/Ideenstatus.gif?v=1402042906"}]}},{"status":"maximized","visible":true,"content":{"id":"13266415","value":""}},{"status":"maximized","visible":true,"content":{"id":"13321386","selectedDocuments":[]}},{"status":"maximized","visible":true,"content":{"id":"13263980","selectedDocuments":[]}},{"status":"maximized","visible":true,"content":{"id":"13282711","value":""}},{"status":"maximized","visible":true,"content":{"id":"14260275","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"14260281","from":"","to":""}},{"status":"maximized","visible":true,"content":{"id":"13278773","value":"MC"}},{"status":"maximized","visible":true,"content":{"id":"13266421","value":""}},{"status":"maximized","visible":false,"content":{"id":"13263869","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263925","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263931","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"13263937","value":""}},{"status":"maximized","visible":false,"content":{"id":"13263943","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263949","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263986","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263992","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13316729","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13263998","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264029","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264035","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264041","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264047","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13264078","value":""}},{"status":"minimized","visible":false,"content":{"id":"13264084","value":""}},{"status":"minimized","visible":false,"content":{"id":"13279637","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13313409","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313415","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313421","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13313452","from":"","to":""}},{"status":"minimized","visible":false,"content":{"id":"13331612","selectedDocuments":[]}},{"status":"minimized","visible":false,"content":{"id":"13331618","selectedDocuments":[]}},{"status":"maximized","visible":false,"content":{"id":"14260287","selectedDocuments":[]}}]}""")
            .param("""csrfToken""", "${csrfToken}")
        )
}
