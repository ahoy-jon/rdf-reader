package fr.jonathanwinandy.javaweb.app

import com.vaadin.terminal.ThemeResource
import org.apache.commons.configuration.PropertiesConfiguration
import twitter4j.auth.AccessToken
import twitter4j.TwitterFactory
import com.vaadin.ui._
import collection.JavaConversions

final object Application {
  lazy val ICON: ThemeResource = new ThemeResource("../runo/icons/16/help.png")
}

final class Application extends com.vaadin.Application {
  override def init: Unit = {
    setTheme("mytheme")
    detailwindow = new Window("Inspector")
    val layout = detailwindow.getContent.asInstanceOf[VerticalLayout]
    layout.setMargin(true)
    layout.setSpacing(true)
    detailwindow.addComponent(new Label("Content in the inspector window"))
    val open = new Button("Inspector", new Button.ClickListener {
      def buttonClick(event: Button#ClickEvent): Unit = {
        if (detailwindow.getParent != null) {
          getMainWindow.removeWindow(detailwindow)
        }
        else {
          getMainWindow.addWindow(detailwindow)
          detailwindow.setPositionX(30)
          detailwindow.setPositionY(100)
          detailwindow.setHeight("40%")
        }
      }
    })
    open.setIcon(Application.ICON)

    val richText = new Label("<h1>Opups <i>AHOY</i></h1>") {
      setContentMode(Label.CONTENT_XHTML)
    }

    val toolbar = new HorizontalLayout {
      setSizeFull
      addComponent(open)
      setComponentAlignment(open, Alignment.TOP_LEFT)
    }


    val twitterlayout = new VerticalLayout
    twitter(twitterlayout)

    val mainLayout = new VerticalLayout {
      addComponent(richText)
      addComponent(toolbar)
      addComponent(twitterlayout)
      setComponentAlignment(richText, Alignment.MIDDLE_LEFT)
      setWidth("750px")
      setSpacing(true)
    }




    val globalLayout = new VerticalLayout {
      addComponent(mainLayout)
      setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER)
    }


    val mainwindow = new Window {
      addComponent(globalLayout)
      setBorder(1)
    }

    this.setMainWindow(mainwindow)


  }

  def twitter(layout: VerticalLayout): Unit = {
    val config = new PropertiesConfiguration("twitter4j.properties")
    val accessToken = new AccessToken(config.getString("twitter.user.token"), config.getString("twitter.user.tokensecret"))
    val factory = new TwitterFactory()
    val twitter = factory.getInstance(accessToken)
    val statuses = twitter.getFriendsTimeline;
    for (status <- JavaConversions.asScalaBuffer(statuses)) {
      layout.addComponent(new Label(status.getUser.getName + ":" +
        status.getText) {
        setHeight("40px")
      })
    }

  }


  private var hlayout: HorizontalLayout = null
  private var detailwindow: Window = null
}