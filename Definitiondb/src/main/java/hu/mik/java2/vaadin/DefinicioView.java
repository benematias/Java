package hu.mik.java2.vaadin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.mik.java2.definition.bean.Definicio;
import hu.mik.java2.service.DefinicioService;

@SuppressWarnings({"serial", "unchecked"})
@SpringView(name = DefinicioView.DEFINICION_VIEW_NAME)
public class DefinicioView extends VerticalLayout implements View {

	protected static final String DEFINICION_VIEW_NAME = "definicion";

	private BeanContainer<Long, Definicio> definicioBean;

	Set<Object> selectedItemIds = new HashSet<Object>();
	
	@Autowired
	private ApplicationContext ctx;

	@Autowired
	@Qualifier("definitionServiceImpl")
	private DefinicioService definicionService;

	Table definicioTable = new Table("List of definitions");

	@Override
	public void enter(ViewChangeEvent event) {
		Page.getCurrent().setTitle("List of definitions");
		this.setMargin(true);
		this.setSpacing(true);
		Component functionComponent = createFunctionLayout();
		this.addComponent(functionComponent);
		this.setComponentAlignment(functionComponent, Alignment.TOP_CENTER);
		this.addComponent(createTable());
		
	}


	protected Component createFunctionLayout() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		horizontalLayout.setMargin(true);
		
		Component searchDefinicioComponent = createSearchField();
		horizontalLayout.addComponent(searchDefinicioComponent);
		horizontalLayout.setComponentAlignment(searchDefinicioComponent, Alignment.BOTTOM_CENTER);
		
		Component subjectsearchDefinicioComponent = createSearchbysubjectField();
		horizontalLayout.addComponent(subjectsearchDefinicioComponent);
		horizontalLayout.setComponentAlignment(subjectsearchDefinicioComponent, Alignment.BOTTOM_CENTER);
		
		Component newDefinicioComponent = createNewDefinicioButton();
		horizontalLayout.addComponent(newDefinicioComponent);
		horizontalLayout.setComponentAlignment(newDefinicioComponent, Alignment.BOTTOM_CENTER);
		
		Component backToMainComponent = backToMainViewButton();
		horizontalLayout.addComponent(backToMainComponent);
		horizontalLayout.setComponentAlignment(backToMainComponent, Alignment.BOTTOM_CENTER);
		
		Component logOutComponent = logOutButton();
		horizontalLayout.addComponent(logOutComponent);
		horizontalLayout.setComponentAlignment(logOutComponent, Alignment.BOTTOM_CENTER);
		return horizontalLayout;
	}

	protected Component createSearchField() {
		final TextField searchfield = new TextField("seahrch for List of definition");
		searchfield.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (searchfield.getValue().isEmpty()) {
					refreshTable();
				} else {
					definicioBean.removeAllItems();
					definicioBean.addAll(definicionService.listDefiniciosByDefinition(searchfield.getValue()));
				}
			}
		});
		return searchfield;
	}
	protected Component createSearchbysubjectField() {
		final TextField subjectsearchfield = new TextField("seahrch for List of subject");
		subjectsearchfield.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (subjectsearchfield.getValue().isEmpty()) {
					refreshTable();
				} else {
					definicioBean.removeAllItems();
					definicioBean.addAll(definicionService.listDefiniciosBySbject(subjectsearchfield.getValue()));
				}
			}
		});
		return subjectsearchfield;
	}
	protected Component createNewDefinicioButton() {
		Button newDefinicioButton = new Button("add a new definition");
		
		newDefinicioButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Definicio definicio = new Definicio();
				ctx.getBean(DefinicionWindow.class).showWindow(definicio, "add a new definition", false, DefinicioView.this);
			}
		});
		
		return newDefinicioButton;
	}
	
	
	
	protected Component backToMainViewButton() {
		Button backButton = new Button("Back to main page");

		backButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(MainView.MAIN_VIEW_NAME);
			}
		});
		return backButton;
	}
	
	protected Component logOutButton() {
		Button logOutButoon = new Button("Exit");

		logOutButoon.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getPage().setLocation(
						VaadinServlet.getCurrent().getServletContext().getContextPath() + "/login?logout=1");
			}
		});
		
		return logOutButoon;
	}

	protected void refreshTable() {
		definicioBean.removeAllItems();
		definicioBean.addAll(definicionService.listDefinicios());
	}

	protected Component createTable() {
		definicioBean = new BeanContainer<Long, Definicio>(Definicio.class);
		definicioBean.setBeanIdProperty("id");
		definicioBean.addAll(definicionService.listDefinicios());
		definicioTable.setContainerDataSource(definicioBean);
		definicioTable.setSizeFull();

		definicioTable.addGeneratedColumn("selector", new ColumnGenerator() {

			@Override
			public Object generateCell(Table source, final Object itemId, Object columnId) {
				CheckBox deleteCB = new CheckBox();
				deleteCB.addValueChangeListener(new ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						if (selectedItemIds.contains(itemId)) {
							selectedItemIds.remove(itemId);
						} else {
							selectedItemIds.add(itemId);
						}
					}
				});

				return deleteCB;
			}
		});

		definicioTable.addGeneratedColumn("viewDefinicio", new ColumnGenerator() {

			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				Button viewDefinicioButton = new Button("Show");
				viewDefinicioButton.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						BeanItem<Definicio> definicioItem = (BeanItem<Definicio>) source.getItem(itemId);
						ctx.getBean(DefinicionWindow.class).showWindow(definicioItem.getBean(), "Show", true,
								DefinicioView.this);
					}
				});

				return viewDefinicioButton;
			}
		});

		definicioTable.addGeneratedColumn("editDefinicio", new ColumnGenerator() {

			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				Button editDefinicioButton = new Button("Edit");

				editDefinicioButton.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						BeanItem<Definicio> definicioItem = (BeanItem<Definicio>) source.getItem(itemId);
						ctx.getBean(DefinicionWindow.class).showWindow(definicioItem.getBean(), "Edit", false,
								DefinicioView.this);
					}
				});
				return editDefinicioButton;
			}
		});
		
		definicioTable.setVisibleColumns("id", "definition", "subject", "registrydate", "viewDefinicio", "editDefinicio");

		
		definicioTable.setColumnHeader("id", "id");
		definicioTable.setColumnHeader("definition", "definition");
		definicioTable.setColumnHeader("subject", "subject");
		definicioTable.setColumnHeader("registrydate", "registrydate");
		definicioTable.setColumnHeader("viewDefinicio", "Show");
		definicioTable.setColumnHeader("editDefinicio", "Edit");

		definicioTable.setColumnAlignment("selector", Align.CENTER);
		definicioTable.setColumnAlignment("id", Align.CENTER);
		definicioTable.setColumnAlignment("definition", Align.CENTER);
		definicioTable.setColumnAlignment("subject", Align.CENTER);
		definicioTable.setColumnAlignment("registrydate", Align.CENTER);
		definicioTable.setColumnAlignment("viewDefinicio", Align.CENTER);
		definicioTable.setColumnAlignment("editDefinicio", Align.CENTER);

		return definicioTable;
	}

}
