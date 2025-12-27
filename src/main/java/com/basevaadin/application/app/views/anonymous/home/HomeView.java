package com.basevaadin.application.app.views.anonymous.home;

import com.basevaadin.application.app.constantes.Constante;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Home")
@Route("")
@Menu(order = Constante.NUMERO_DOS, icon = "line-awesome/svg/file.svg")
@AnonymousAllowed
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Header con logo
        add(createHeader());

        // Hero Section
        add(createHeroSection());

        // Features Section
        add(createFeaturesSection());

        // Footer
        add(createFooter());
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setSpacing(true);
        header.getStyle()
                .set("background-color", "#C41E3A")
                .set("color", "white")
                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.1)");

        // Logo (usa la imagen cuadrada generada)
        Image logo = new Image("generated_image_1766790180_0.png", "Órale Logo");
        logo.setHeight("60px");
        logo.setWidth("60px");
        logo.getStyle().set("border-radius", "8px");

        // Nombre del restaurante
        H2 brandName = new H2("Órale");
        brandName.getStyle()
                .set("margin", "0")
                .set("color", "white")
                .set("font-weight", "bold");

        // Menú de navegación
        HorizontalLayout nav = new HorizontalLayout();
        nav.setSpacing(true);
        nav.getStyle().set("margin-left", "auto");

        Button menuBtn = createNavButton(getTranslation("nav.menu", "Menu"));
        Button aboutBtn = createNavButton(getTranslation("nav.about", "About"));
        Button contactBtn = createNavButton(getTranslation("nav.contact", "Contact"));
        Button orderBtn = new Button(getTranslation("nav.order", "Order Now"));
        orderBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        nav.add(menuBtn, aboutBtn, contactBtn, orderBtn);

        header.add(logo, brandName, nav);
        header.setAlignItems(Alignment.CENTER);

        return header;
    }

    private VerticalLayout createHeroSection() {
        VerticalLayout hero = new VerticalLayout();
        hero.setSizeFull();
        hero.setAlignItems(Alignment.CENTER);
        hero.setJustifyContentMode(JustifyContentMode.CENTER);
        hero.getStyle()
                .set("background", "linear-gradient(135deg, #C41E3A 0%, #8B1528 100%)")
                .set("color", "white")
                .set("min-height", "500px")
                .set("padding", "60px 20px");

        // Logo grande central
        Image heroLogo = new Image("generated_image_1766790180_0.png", "Órale");
        heroLogo.setWidth("200px");
        heroLogo.setHeight("200px");
        heroLogo.getStyle().set("margin-bottom", "20px");

        H1 title = new H1(getTranslation("hero.title", "Authentic Mexican Flavor"));
        title.getStyle()
                .set("margin", "0")
                .set("font-size", "3rem")
                .set("text-align", "center")
                .set("font-weight", "bold");

        Paragraph subtitle = new Paragraph(
                getTranslation("hero.subtitle",
                        "Experience the true taste of Mexico with our traditional recipes and fresh ingredients")
        );
        subtitle.getStyle()
                .set("font-size", "1.2rem")
                .set("text-align", "center")
                .set("max-width", "600px")
                .set("margin", "20px 0");

        HorizontalLayout ctaButtons = new HorizontalLayout();
        Button orderNowBtn = new Button(getTranslation("hero.order", "Order Now"),
                new Icon(VaadinIcon.CART));
        orderNowBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        orderNowBtn.getStyle()
                .set("background-color", "white")
                .set("color", "#C41E3A");

        Button viewMenuBtn = new Button(getTranslation("hero.menu", "View Menu"),
                new Icon(VaadinIcon.BOOK));
        viewMenuBtn.addThemeVariants(ButtonVariant.LUMO_LARGE, ButtonVariant.LUMO_CONTRAST);

        ctaButtons.add(orderNowBtn, viewMenuBtn);
        ctaButtons.getStyle().set("margin-top", "30px");

        hero.add(heroLogo, title, subtitle, ctaButtons);

        return hero;
    }

    private VerticalLayout createFeaturesSection() {
        VerticalLayout features = new VerticalLayout();
        features.setWidthFull();
        features.setPadding(true);
        features.getStyle()
                .set("padding", "60px 20px")
                .set("background-color", "#f5f5f5");

        H2 featuresTitle = new H2(getTranslation("features.title", "Why Choose Órale?"));
        featuresTitle.getStyle()
                .set("text-align", "center")
                .set("color", "#C41E3A")
                .set("margin-bottom", "40px");

        HorizontalLayout featureCards = new HorizontalLayout();
        featureCards.setWidthFull();
        featureCards.setJustifyContentMode(JustifyContentMode.CENTER);
        featureCards.setSpacing(true);
        featureCards.getStyle().set("flex-wrap", "wrap");

        featureCards.add(
                createFeatureCard(VaadinIcon.HEART,
                        getTranslation("feature1.title", "Authentic Recipes"),
                        getTranslation("feature1.desc", "Traditional Mexican recipes passed down through generations")),
                createFeatureCard(VaadinIcon.HEART,
                        getTranslation("feature2.title", "Fresh Ingredients"),
                        getTranslation("feature2.desc", "We use only the freshest, locally-sourced ingredients")),
                createFeatureCard(VaadinIcon.CLOCK,
                        getTranslation("feature3.title", "Fast Service"),
                        getTranslation("feature3.desc", "Quick preparation without compromising quality"))
        );

        features.add(featuresTitle, featureCards);
        features.setAlignItems(Alignment.CENTER);

        return features;
    }

    private VerticalLayout createFeatureCard(VaadinIcon iconType, String title, String description) {
        VerticalLayout card = new VerticalLayout();
        card.setWidth("300px");
        card.setPadding(true);
        card.setSpacing(true);
        card.getStyle()
                .set("background-color", "white")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 12px rgba(0,0,0,0.1)")
                .set("text-align", "center");

        Icon icon = new Icon(iconType);
        icon.setSize("48px");
        icon.getStyle().set("color", "#C41E3A");

        H3 cardTitle = new H3(title);
        cardTitle.getStyle()
                .set("margin", "10px 0")
                .set("color", "#333");

        Paragraph cardDesc = new Paragraph(description);
        cardDesc.getStyle()
                .set("color", "#666")
                .set("font-size", "0.95rem");

        card.add(icon, cardTitle, cardDesc);
        card.setAlignItems(Alignment.CENTER);

        return card;
    }

    private HorizontalLayout createFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setPadding(true);
        footer.getStyle()
                .set("background-color", "#1a1a1a")
                .set("color", "white")
                .set("padding", "40px 20px")
                .set("margin-top", "auto");

        VerticalLayout contactInfo = new VerticalLayout();
        contactInfo.setSpacing(false);

        H3 contactTitle = new H3(getTranslation("footer.contact", "Contact Us"));
        contactTitle.getStyle().set("color", "white");

        Paragraph phone = new Paragraph("📞 +1 (555) 123-4567");
        Paragraph email = new Paragraph("✉️ info@orale.com");
        Paragraph address = new Paragraph("📍 123 Mexican Street, Food City");

        contactInfo.add(contactTitle, phone, email, address);

        VerticalLayout socialMedia = new VerticalLayout();
        socialMedia.setSpacing(false);

        H3 socialTitle = new H3(getTranslation("footer.follow", "Follow Us"));
        socialTitle.getStyle().set("color", "white");

        HorizontalLayout socialIcons = new HorizontalLayout();
        socialIcons.add(
                createSocialButton(VaadinIcon.FACEBOOK),
                //createSocialButton(VaadinIcon.),
                createSocialButton(VaadinIcon.TWITTER)
        );

        socialMedia.add(socialTitle, socialIcons);

        footer.add(contactInfo, socialMedia);
        footer.setJustifyContentMode(JustifyContentMode.AROUND);

        return footer;
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_CONTRAST);
        return btn;
    }

    private Button createSocialButton(VaadinIcon icon) {
        Button btn = new Button(new Icon(icon));
        btn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_CONTRAST);
        btn.getStyle()
                .set("color", "white")
                .set("border", "1px solid white")
                .set("border-radius", "50%");
        return btn;
    }
}
