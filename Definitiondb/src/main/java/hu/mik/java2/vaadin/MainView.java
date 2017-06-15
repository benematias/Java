package hu.mik.java2.vaadin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
@SpringView(name = MainView.MAIN_VIEW_NAME)
public class MainView extends VerticalLayout implements View {

	protected static final String MAIN_VIEW_NAME = "";
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 String role = auth.getAuthorities().toString(); 
	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("Main page");
		this.setMargin(true);
		this.setSpacing(true);
		Label htmlLabel = new Label("<h1>Wellcome!</h1>");
		htmlLabel.setContentMode(ContentMode.HTML);
		htmlLabel.setSizeUndefined();
		this.addComponent(htmlLabel);
		this.setComponentAlignment(htmlLabel, Alignment.TOP_CENTER);

		Button navToDefinicioListButoon = new Button("Definicions");
	

		if(role.contains("ROLE_ADMIN")){
			navToDefinicioListButoon.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo(DefinicioViewadmin.DEFINICION_VIEW_NAME);
				}});
			}else{

		navToDefinicioListButoon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(DefinicioView.DEFINICION_VIEW_NAME);
			}
		});
			}
		this.addComponent(navToDefinicioListButoon);
		this.setComponentAlignment(navToDefinicioListButoon, Alignment.BOTTOM_CENTER);
			
		Button logOutButoon = new Button("Logout");

		logOutButoon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getPage().setLocation(
						VaadinServlet.getCurrent().getServletContext().getContextPath() + "/login?logout=1");
			}
		});

		this.addComponent(logOutButoon);
		this.setComponentAlignment(logOutButoon, Alignment.BOTTOM_CENTER);
	}

}
