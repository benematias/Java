package hu.mik.java2.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringUI(path = "/definicioUI")
@Widgetset("hu.mik.java2.vaadin.DefinicioWidgetSet")
public class DefinicioUI extends UI{

	@Autowired
	private ViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(viewProvider);
	}
	
}
