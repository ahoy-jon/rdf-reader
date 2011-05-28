package rdfreader

import org.scalatest.Suite
import org.apache.commons.configuration.PropertiesConfiguration
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.auth.RequestToken

/**
 * @author Un-Jon
 * @brief Test on twitter4j Configuration files
 */

class TwitterTest extends Suite {
  val config = new PropertiesConfiguration("twitter4j.properties")
  val consKeyLabel = "twitter4j.oauth.consumerKey"
  val consSecretLabel = "twitter4j.oauth.consumerSecret"

  /**
   * Test basique sur le contenu du fichier de propriété de twitter4j
   */
  def testProperties() {

    val consKey = config.getString(consKeyLabel)
    val consSecret = config.getString(consSecretLabel)
    for (value <- consKey :: consSecret :: Nil) {
      assert(value.length > 0)
    }
    isOAuthKey(consKey)
    isOAuthSecret(consSecret)

    def isOAuthKey(key: String): Unit = {
      val keyRegExp = """([0-9a-zA-Z]{21})""".r
      val keyRegExp(oauthkey) = key
    }

    def isOAuthSecret(secret: String): Unit = {
      val secretRegExp = """([0-9a-zA-Z]+)""".r
      val secretRegExp(oauthsecret) = secret
    }

  }

  def testTwitter() {
    val twitter: Twitter = (new TwitterFactory()).getInstance()
    /* twitter.setOAuthConsumer(config.getString(consKeyLabel),
      config.getString(consSecretLabel))         */

    val requestToken = twitter.getOAuthRequestToken()
    println(requestToken.getAuthorizationURL())


  }

}