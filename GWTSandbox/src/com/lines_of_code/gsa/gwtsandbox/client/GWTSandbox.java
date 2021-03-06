package com.lines_of_code.gsa.gwtsandbox.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTSandbox implements EntryPoint {

	final String[] allowedExt = new String[] { "xls", "xlsx", "xml", "csv",
			"txt" };
	final String productiveURL = GWT.getHostPageBaseURL() + "webportal/servlet/CatalogImport";

	/**
	 * A simple data type that represents a contact.
	 */
	private static class Catalog {
		private final String shortDesc;
		private final String category;
		private final double price;
		private final String aid;

		public Catalog(String shortDesc, String category, double price,
				String aid) {
			super();
			this.shortDesc = shortDesc;
			this.category = category;
			this.price = price;
			this.aid = aid;
		}

	}

	/**
	 * The list of data to display.
	 */
	private static final List<Catalog> CATALOG = Arrays
.asList(new Catalog("Foo Bar 1", "foobar", Random.nextDouble() * 100, "0815"),
		new Catalog("Foo Bar 2", "foobar", Random.nextDouble() * 100, "0816"), 
		new Catalog("Foo Bar 3", "foobar", Random.nextDouble() * 100, "0817"),
		new Catalog("Foo Bar 4", "foobar", Random.nextDouble() * 100, "0818"),
		new Catalog("Foo Bar 5", "foobar", Random.nextDouble() * 100, "0819"),
		new Catalog("Foo Bar 6", "foobar", Random.nextDouble() * 100, "0820"),
		new Catalog("Foo Bar 7", "foobar", Random.nextDouble() * 100, "0821"),
		new Catalog("Foo Bar 8", "foobar", Random.nextDouble() * 100, "0822"),
		new Catalog("Foo Bar 9", "foobar", Random.nextDouble() * 100, "0823"),
		new Catalog("Foo Bar 10", "foobar", Random.nextDouble() * 100, "0824"),
		new Catalog("Foo Bar 11", "foobar", Random.nextDouble() * 100, "0825"),
		new Catalog("Foo Bar 12", "foobar", Random.nextDouble() * 100, "0826"),
		new Catalog("Foo Bar 13", "foobar", Random.nextDouble() * 100, "0828"),
		new Catalog("Foo Bar 14", "foobar", Random.nextDouble() * 100, "0829"),
		new Catalog("Foo Bar 15", "foobar", Random.nextDouble() * 100, "0830"),
		new Catalog("Foo Bar 16", "foobar", Random.nextDouble() * 100, "0831"),
		new Catalog("Foo Bar 17", "foobar", Random.nextDouble() * 100, "0832"),
		new Catalog("Foo Bar 18", "foobar", Random.nextDouble() * 100, "0833"),
		new Catalog("Foo Bar 19", "foobar", Random.nextDouble() * 100, "0834"),
		new Catalog("Foo Bar 20", "foobar", Random.nextDouble() * 100, "0835")
	);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TabLayoutPanel tabLayout = new TabLayoutPanel(1.5, Unit.EM);
		tabLayout.setHeight("600px");
		final VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("100%");

		VerticalPanel vPanel2 = new VerticalPanel();

		// Center everything in the panel.
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		// Add a FormPanel widget.
		final FormPanel form = new FormPanel();
		form.setAction("http://192.168.19.111:8080/webportal/servlet/CatalogValidator");
		form.setMethod(FormPanel.METHOD_POST);
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setWidget(vPanel);
		
		// Add a Upload widget. 
		final FileUpload upload = new FileUpload();
		upload.setWidth("10px");
		upload.setTitle("Katalogdatei auswaehlen ...");

		// Add a label to display post information
		final Label postLabel = new Label();
		vPanel.add(postLabel);
		
		// Add a form handler.
		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				// This event is fired just before the form is submitted. We can
				GWT.log("Uploading file: '" + upload.getFilename() + "' to: "
						+ form.getAction());
				postLabel.setText("POST '" + upload.getFilename() + "' to: " + form.getAction());
			}
		});
		
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// TODO Process Catalog Validator response
				
			}
		});
		

		// add a button to upload the file.
		final Button validateButton = new Button("Katalog validieren");
		validateButton.setEnabled(false);
		validateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}	
		});

		// Add file recognition.
		upload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				boolean fileExtValid = false;
				String fileName = upload.getFilename();
				for (String ext : allowedExt) {
					if (fileName.toLowerCase().endsWith(ext)) {
						fileExtValid = true;
					}
				}
				if (fileExtValid) {
					if (fileName.endsWith("xml")) {
						validateButton.setText("BMEcat validieren");
						upload.setName("standalonebmecat");
					} else if (fileName.endsWith("xls")
							|| fileName.endsWith("xlsx")) {
						validateButton.setText("Excel Katalog validieren");
						upload.setName("excel");
					} else if (fileName.endsWith("txt")
							|| fileName.endsWith("csv")) {
						validateButton.setText("CSV Katalog validieren");
						upload.setName("textfile");
					}
					validateButton.setEnabled(true);
				} else {
					validateButton.setText("Katalog validieren");
					validateButton.setEnabled(false);
				}
			}

		});
		

		vPanel.add(upload);
		vPanel.add(validateButton);

		/* |----------------------------------|
		 * |          Catalog Table           |
		 * |----------------------------------|
		 */

		// Create a CellTable.
		CellTable<Catalog> table = new CellTable<Catalog>();
		table.setPageSize(10);
		table.setPageStart(0);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the shortDesc.
		TextColumn<Catalog> shortDescColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.shortDesc;
			}
		};
		table.addColumn(shortDescColumn, "Kurzbezeichnung");

		TextColumn<Catalog> categoryColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.category;
			}
		};
		table.addColumn(categoryColumn, "Kategorie");

		// Add a column to show the price.
		TextColumn<Catalog> priceColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return String.valueOf(object.price);
			}
		};
		table.addColumn(priceColumn, "Preis");

		// Add a text column to show the address.
		TextColumn<Catalog> aidColumn = new TextColumn<Catalog>() {
			@Override
			public String getValue(Catalog object) {
				return object.aid;
			}
		};
		table.addColumn(aidColumn, "AID");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Catalog> selectionModel = new SingleSelectionModel<Catalog>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Catalog selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("Artikel: " + selected.shortDesc);
						}
					}
				});

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		table.setRowCount(CATALOG.size(), true);

		// Push the data into the widget.
		table.setRowData(0, CATALOG);

		ListDataProvider<Catalog> dataProvider = new ListDataProvider<Catalog>(
				CATALOG);
		dataProvider.addDataDisplay(table);


		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER,
				pagerResources, false, 00, false);
		pager.setDisplay(table);

		
		vPanel2.add(table);
		vPanel2.add(pager);

		tabLayout.add(form, "Katalogimport");
		tabLayout.add(vPanel2, "Katalog bearbeiten");
		RootPanel.get("tabLayout").add(tabLayout);
	}
}
