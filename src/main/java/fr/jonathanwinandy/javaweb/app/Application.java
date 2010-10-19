package fr.jonathanwinandy.javaweb.app;


import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;



public final class Application extends com.vaadin.Application{


    private HorizontalLayout hlayout;
    private Window mainwindow;
    private Label richText;
    private Window detailwindow;
    private static final ThemeResource ICON = new ThemeResource("../runo/icons/16/help.png");
  

    @Override
      public void init() {



        setTheme("mytheme");


        detailwindow = new Window("Inspector");
        VerticalLayout layout = (VerticalLayout) detailwindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);
        detailwindow.addComponent(new Label("Content in the inspector window"));

        Button open = new Button("Inspector", new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                if (detailwindow.getParent() != null) {

                    getMainWindow().removeWindow(detailwindow);
                } else {

                    getMainWindow().addWindow(detailwindow);
                    detailwindow.setPositionX(30);
                    detailwindow.setPositionY(100);
                    detailwindow.setHeight("40%");

                }
            }
        });
        open.setIcon(ICON);


        VerticalLayout globalLayout= new VerticalLayout();


        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("750px");
        mainLayout.setSpacing(true);




        globalLayout.addComponent(mainLayout);
        globalLayout.setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        richText = new Label("<h1>Opups</h1>");
        richText.setContentMode(Label.CONTENT_XHTML);

        mainLayout.addComponent(richText);
        mainLayout.setComponentAlignment(richText, Alignment.MIDDLE_LEFT);


        mainwindow = new Window();
        setMainWindow(mainwindow);
        mainwindow.setBorder(1);
        mainwindow.addComponent(globalLayout);


        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addComponent(open);
        toolbar.setSizeFull();
        toolbar.setComponentAlignment(open, Alignment.TOP_LEFT);
        mainLayout.addComponent(toolbar);


    }







}

