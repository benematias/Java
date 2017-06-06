package hu.mik.java2.vaadin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import hu.mik.java2.definition.bean.Definicio;
import hu.mik.java2.service.DefinicioService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope(scopeName = "prototype")
public class DefinicioWindow extends Window{
	
	@Autowired
	@Qualifier("definicioServiceImpl")
	private DefinicioService definicioService;


	public void showWindow(Definicio definicio, String title, boolean isReadonly, DefinicioView definicioView) {
		this.setHeight("50%");
		this.setWidth("50%");
		this.center();
		this.setCaption(title);
		this.setContent(createEditLayout(definicio, isReadonly, definicioView));
		UI.getCurrent().addWindow(this);
		
	}

	private Component createEditLayout(Definicio definicio, boolean isReadOnly, final DefinicioView definicioView) {
		final BeanFieldGroup<Definicio> definicioBeanField = new BeanFieldGroup<Definicio>(Definicio.class);
		definicioBeanField.setItemDataSource(definicio);
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		Component formComponent = createFormLayout(definicioBeanField, isReadOnly);
		formComponent.setSizeUndefined();
		verticalLayout.addComponent(formComponent);
		verticalLayout.setComponentAlignment(formComponent, Alignment.BOTTOM_CENTER);
		
		Button saveButton = new Button("Save");
		saveButton.setVisible(!isReadOnly);

		saveButton.addClickListener(new Button.ClickListener() {


			@Override
			public void buttonClick(ClickEvent event) {
				try {
					definicioBeanField.commit();
					Definicio bean = definicioBeanField.getItemDataSource().getBean();
					if (bean.getId() == null) {
						definicioService.saveDefinicio(bean);
					} else {
						definicioService.updateDefinicio(bean);
					}
					DefinicioWindow.this.close();
					Notification.show("Success");
					definicioView.refreshTable();
				} catch (Exception e) {
					Notification.show("Error");
				}
			}
		});
		verticalLayout.addComponent(saveButton);
		verticalLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_CENTER);

		return verticalLayout;
	}

	private Component createFormLayout(BeanFieldGroup<Definicio> definicioBeanField, boolean isReadonly) {
		FormLayout formLayout = new FormLayout();
		TextField titleField = definicioBeanField.buildAndBind("definition ", "definition", TextField.class);
		titleField.setNullRepresentation("");
		titleField.setReadOnly(isReadonly);
		formLayout.addComponent(titleField);
		TextField authorField = definicioBeanField.buildAndBind("subject", "subject", TextField.class);
		authorField.setNullRepresentation("");
		authorField.setReadOnly(isReadonly);
		formLayout.addComponent(authorField);
		TextField pubYearField = definicioBeanField.buildAndBind("registrydate", "registrydate", TextField.class);
		pubYearField.setNullRepresentation("");
		pubYearField.setReadOnly(isReadonly);
		formLayout.addComponent(pubYearField);
		RichTextArea descriptionField = definicioBeanField.buildAndBind("description", "description", RichTextArea.class);
		descriptionField.setNullRepresentation("");
		descriptionField.setReadOnly(isReadonly);
		formLayout.addComponent(descriptionField);
		return formLayout;
	}

}
