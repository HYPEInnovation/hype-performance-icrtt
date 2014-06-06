package de.hype.performance.icrtt
import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._

class ICRTTSimulation extends Simulation {

	val httpConf = httpConfig
			.baseURL("http://localhost")
			.acceptHeader("image/png,image/*;q=0.8,*/*;q=0.5")
			.acceptEncodingHeader("gzip, deflate")
			.acceptLanguageHeader("de,en-US;q=0.7,en;q=0.3")
			.connection("keep-alive")
			.doNotTrackHeader("1")
			.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0")


	setUp(
        ScenarioCreateCIC.scn.users(1).protocolConfig(httpConf),
        ScenarioReportIL6MC.scn.users(1).protocolConfig(httpConf)
    )
}