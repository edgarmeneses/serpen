package com.serpen.interfaces;

import java.util.List;

import com.serpen.error.connection.ErrorConnection;
import com.serpen.logic.entity.Role;
import com.serpen.logic.entity.User;
import com.serpen.persistence.control.ControlGeneral;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
/**
 * Universidad Pedagogica y Tecnologica de Colombia 
 * @author Eliana Ayala
 *         Daniela Blanco 
 *         Diana Gonzalez
 *         Edgar Meneses
 *Clase de la interfaz que se encarga de pintar la ventana de Panel control List
 *extiende de Panel
 */
public class PanelListRol extends Panel{

	/**
	 * Atributos de la clase panelControlList 
	 */
	private Label lblUser;
	private TextField txtUser;
	private ComboBox boxRol;
	private Button btnSearch;
	private Button btnReturn;
	private Table table;
	private Panel pnlMenuRol;
	private Panel pnlTableRol;
	private Navigator navigator;
	private ControlGeneral controlGeneral;

	/**
	 * Constructor que se encarga de pedir por parametro 
	 * @param Navegator
	 * @param  Control General
	 */
	public PanelListRol(Navigator navigator, ControlGeneral control){

		this.navigator=navigator;
		this.controlGeneral=control;

		FormLayout layoutPrincipal = new FormLayout();
		layoutPrincipal.setVisible(true);

		FormLayout layoutPanel = new FormLayout();
		layoutPanel.setVisible(true);

		HorizontalLayout layoutMenu = new HorizontalLayout();
		layoutMenu.setVisible(true);

		pnlMenuRol = new Panel();
		pnlMenuRol.setSizeFull();
		pnlMenuRol.setWidth("1000");
		pnlMenuRol.setHeight("100");

		pnlTableRol = new Panel();
		pnlTableRol.setSizeFull();
		pnlTableRol.setWidth("1000px");
		pnlTableRol.setHeight("300px");
		
		


		lblUser = new Label("Usuario");		
		lblUser.setWidth("100px");
		lblUser.setHeight("50px");
		lblUser.setVisible(true);

		txtUser = new TextField();
		txtUser.setWidth("150px");
		txtUser.setHeight("50px");
		txtUser.setRequired(true);
		txtUser.setValue("");
		txtUser.setNullRepresentation("");

		boxRol = new ComboBox("");
		boxRol.setVisible(true);
		boxRol.setValue("");

		btnSearch= new  Button("Buscar");

		btnSearch.setWidth("150px");
		btnSearch.setHeight("50px");
		btnSearch.setVisible(true);

		btnReturn= new  Button("Regresar");
		btnReturn.setWidth("150px");
		btnReturn.setHeight("50px");
		btnReturn.setVisible(true);
		btnReturn.addClickListener(new Button.ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				navigator.navigateTo(Administrator.NAMEADMINISTRATOR);
			}

		});

		table = new Table();

		table.addContainerProperty("Nombre", String.class, null);
		table.addContainerProperty("Control", PanelButtonRol.class, null);

		table.setPageLength(table.size());  
		table.setWidth("80%"); 
		table.setHeight("170px");
		table.setFooterVisible(true);
		
		fillTable();

		layoutMenu.addComponent(lblUser);
		layoutMenu.addComponent(txtUser);
		layoutMenu.addComponent(boxRol);
		layoutMenu.addComponent(btnSearch);
		layoutPanel.addComponent(table);
		layoutPanel.addComponent(btnReturn);


		this.pnlMenuRol.setContent(layoutMenu);
		this.pnlTableRol.setContent(layoutPanel);

		layoutPrincipal.addComponent(pnlMenuRol);
		layoutPrincipal.addComponent(pnlTableRol);

		setContent(layoutPrincipal);

	}
	
	public void fillTable(){
		try {
			List<Role> roles = controlGeneral.getRole().list();
			table.removeAllItems();
			for (int i = 0; i < roles.size(); i++) {
				table.addItem(fillColumn(roles.get(i)),i);
			}
		} catch (ErrorConnection e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Object[] fillColumn(Role role){
		return new Object[]{role.getName(), new PanelButtonRol(role, controlGeneral, this)};
	}
	
}
