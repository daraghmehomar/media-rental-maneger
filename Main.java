package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import project.Album;
import project.Customer;
import project.Game;
import project.MediaRentalManger;
import project.Movie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class Main extends Application {
	static Scene scene1;
	static Scene sceneCus;
	static Scene sceneMed;
	static Scene sceneRent;
	static Stage window;
	static project.MediaRentalManger m = new MediaRentalManger();

	@Override
	public void start(Stage stage) {
		ImageView custicon = new ImageView(new Image("225851.png"));
		custicon.setFitHeight(30);
		custicon.setFitWidth(30);
		ImageView medicon = new ImageView(new Image("2206324.png"));
		medicon.setFitHeight(30);
		medicon.setFitWidth(30);
		ImageView renticon = new ImageView(new Image("2590468.png"));
		renticon.setFitHeight(30);
		renticon.setFitWidth(30);
		window = stage;
		ImageView wallpaper = new ImageView(new Image("rent.jpg"));
		wallpaper.setFitHeight(150);
		wallpaper.setFitWidth(400);
		Button cus = new Button();
		cus.setGraphic(custicon);
		Label cusl = new Label("customer");
		cus.setOnAction(e -> {
			window.setScene(customer());
			window.setMaximized(true);
			window.show();
		});
		Button med = new Button();
		med.setGraphic(medicon);
		Label medl = new Label("media");

		med.setOnAction(e -> {
			window.setScene(media());
			window.setMaximized(true);
			window.show();
		});
		Button rent = new Button();
		rent.setGraphic(renticon);
		Label rentl = new Label("rent");

		rent.setOnAction(e -> {
			window.setScene(rent1());
			window.setMaximized(true);
			window.show();
		});
		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(20);
		gp.setAlignment(Pos.CENTER);
		gp.add(cusl, 0, 0);
		gp.add(cus, 1, 0);
		gp.add(medl, 0, 1);
		gp.add(med, 1, 1);
		gp.add(rentl, 0, 2);
		gp.add(rent, 1, 2);
		gp.add(wallpaper, 4, 0);gp.setColumnSpan(wallpaper, 1);
		gp.setRowSpan(wallpaper, 3);
		scene1 = new Scene(gp, 1000, 500);
		window.setScene(scene1);
		window.setMaximized(true);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Scene customer() {
		Button addb = new Button("add customer");
		Button deleteb = new Button("delete customer");
		Button searchb = new Button("search customer");
		Button updateb = new Button("update customer info");
		Button printb = new Button("print all info");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		printb.setOnAction(e -> {
			window.setScene(printInfoCustomer());
			window.setMaximized(true);
			window.show();
		});
		addb.setOnAction(e -> {
			window.setScene(addCustomer());
			window.setMaximized(true);
			window.show();
		});

		backb.setOnAction(e -> {
			window.setScene(scene1);
			window.setMaximized(true);
			window.show();
		});
		deleteb.setOnAction(e -> {
			window.setScene(deleteCustomer());
			window.setMaximized(true);
			window.show();
		});
		searchb.setOnAction(e -> {
			window.setScene(searchCustomer());
			window.setMaximized(true);
			window.show();
		});
		updateb.setOnAction(e -> {
			window.setScene(updateCustomer());
			window.setMaximized(true);
			window.show();
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.add(addb, 0, 1);
		gp.add(deleteb, 0, 2);
		gp.add(searchb, 0, 3);
		gp.add(updateb, 0, 4);
		gp.add(printb, 0, 5);
		gp.add(backb, 5, 5);
		gp.setVgap(20);
		gp.setHgap(20);
		sceneCus = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return sceneCus;
	}

	public static Scene deleteCustomer() {
		Label idl = new Label("Customer ID");
		TextField idtf = new TextField();
		HBox idhb = new HBox(idl, idtf);
		idhb.setAlignment(Pos.CENTER);
		idhb.setSpacing(40);
		Button disb = new Button("Display");
		Label infol = new Label("info");
		HBox infohb = new HBox(infol);
		infohb.setAlignment(Pos.CENTER);
		disb.setOnAction(e -> {
			try {
				infol.setText(m.searchCustomer(Integer.parseInt(idtf.getText().trim())));
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		ImageView deleteicon = new ImageView(new Image("delete.png"));
		deleteicon.setFitHeight(30);
		deleteicon.setFitWidth(30);
		Button deleteb = new Button();deleteb.setGraphic(deleteicon);
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		HBox hb = new HBox(deleteb, backb);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(400);
		deleteb.setOnAction(e -> {
			try {
				m.deleteCustomer(Integer.parseInt(idtf.getText().trim()));
				idtf.setText("");
				infol.setText("info");
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		backb.setOnAction(e -> {
			window.setScene(sceneCus);
			window.show();
		});
		VBox vb = new VBox(idhb, disb, infohb, hb);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);

		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene addCustomer() {
		Label idL = new Label("ID");
		Label nameL = new Label("name");
		Label addressL = new Label("address");
		TextField idtf = new TextField();
		TextField nametf = new TextField();
		TextField addresstf = new TextField();
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		ImageView addicon = new ImageView(new Image("69355-200.png"));
		addicon.setFitHeight(30);
		addicon.setFitWidth(30);
		Button addb = new Button();addb.setGraphic(addicon);

		ToggleGroup tg = new ToggleGroup();
		RadioButton lrb = new RadioButton("Limited");
		lrb.setToggleGroup(tg);
		lrb.setSelected(true);
		RadioButton unlrb = new RadioButton("UnLimited");
		unlrb.setToggleGroup(tg);
		addb.setOnAction(e -> {
			try {
				if (unlrb.isSelected())
					m.addCustomer(Integer.parseInt(idtf.getText().trim()), nametf.getText(), addresstf.getText(),
							"UnLimited");
				else
					m.addCustomer(Integer.parseInt(idtf.getText().trim()), nametf.getText(), addresstf.getText(),
							"Limited");
				idtf.setText("");
				nametf.setText("");
				addresstf.setText("");
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		backb.setOnAction(e -> {
			window.setScene(sceneCus);
			window.show();
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.add(idL, 0, 0);
		gp.add(idtf, 2, 0);
		gp.add(nameL, 0, 1);
		gp.add(nametf, 2, 1);
		gp.add(addressL, 0, 2);
		gp.add(addresstf, 2, 2);
		gp.add(lrb, 1, 3);
		gp.add(unlrb, 3, 3);
		gp.add(addb, 2, 4);
		gp.add(backb, 5, 4);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene searchCustomer() {
		Label idl = new Label("Customer ID");
		TextField idtf = new TextField();
		HBox hb = new HBox(20, idl, idtf);
		hb.setAlignment(Pos.CENTER);
		Button disb = new Button("Display");
		Label infol = new Label("info");
		disb.setOnAction(e -> {
			try {
				infol.setText(m.searchCustomer(Integer.parseInt(idtf.getText().trim())));
			} catch (Exception e2) {
				System.out.println("Error!");
			}

		});
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setAlignment(Pos.BASELINE_LEFT);
		backb.setOnAction(e -> {
			window.setScene(sceneCus);
			window.show();
		});
		VBox vb = new VBox(20, hb, disb, infol, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene updateCustomer() {
		Label idl = new Label("ID");
		TextField idtf = new TextField();
		Button disb = new Button("search");
		HBox idhb = new HBox(20, idl, idtf, disb);

		Label namel = new Label("Name");
		Label nnamel = new Label("new Name");
		TextField nametf = new TextField();
		HBox namehb = new HBox(30, namel, nnamel, nametf);

		Label addl = new Label("Address");
		Label naddl = new Label("new address");
		TextField addtf = new TextField();
		HBox addhb = new HBox(20, addl, naddl, addtf);

		Button upb = new Button("update");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		ToggleGroup tg = new ToggleGroup();
		RadioButton lrb = new RadioButton("Limited");
		lrb.setToggleGroup(tg);
		RadioButton unlrb = new RadioButton("UnLimited");
		unlrb.setToggleGroup(tg);
		HBox hb = new HBox(20, lrb, unlrb);
		backb.setOnAction(e -> {
			window.setScene(sceneCus);
			window.show();
		});
		upb.setOnAction(e -> {
			try {
				if (lrb.isSelected())
					m.updatecustomer(Integer.parseInt(idtf.getText().trim()), nametf.getText(), addtf.getText(),
							"Limited");
				else
					m.updatecustomer(Integer.parseInt(idtf.getText().trim()), nametf.getText(), addtf.getText(),
							"UnLimited");
				idtf.setText("");
				addtf.setText("");
				nametf.setText("");
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		disb.setOnAction(e -> {
			try {
				Customer cus = m.customer(Integer.parseInt(idtf.getText().trim()));
				if (cus != null) {
					namel.setText(cus.getName());
					addl.setText(cus.getAddress());
				}
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		HBox bhb = new HBox(200, upb, backb);

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(20);
		gp.setVgap(20);
		gp.add(idhb, 0, 0);
		gp.add(namehb, 0, 1);
		gp.add(addhb, 0, 2);
		gp.add(hb, 0, 3);
		gp.add(bhb, 0, 4);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;

	}

	public static Scene media() {
		Button addb = new Button("add media");
		Button deleteb = new Button("delete media");
		Button searchb = new Button("search media");
		Button updateb = new Button("update media info");
		Button printb = new Button("print all info");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);

		addb.setOnAction(e -> {
			window.setScene(addMedia());
			window.show();

		});
		deleteb.setOnAction(e -> {

			window.setScene(deleteMedia());
			window.show();
		});
		searchb.setOnAction(e -> {
			window.setScene(searchMedia());
			window.show();
		});
		updateb.setOnAction(e -> {

			window.setScene(updateMedia());
			window.show();
		});
		printb.setOnAction(e -> {
			window.setScene(printInfoMedia());
			window.show();
		});
		backb.setOnAction(e -> {
			window.setScene(scene1);
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.add(addb, 0, 1);
		gp.add(deleteb, 0, 2);
		gp.add(searchb, 0, 3);
		gp.add(updateb, 0, 4);
		gp.add(printb, 0, 5);
		gp.add(backb, 5, 5);
		gp.setVgap(20);
		gp.setHgap(20);
		sceneMed = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return sceneMed;
	}

	public static Scene printInfoCustomer() {

		TextArea l = new TextArea(m.getAllCustomersInfo());
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setAlignment(Pos.BASELINE_LEFT);
		backb.setOnAction(e -> {
			window.setScene(sceneCus);
			window.show();
		});
		VBox vb = new VBox(20, l, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene addMedia() {

		Button albumb = new Button("Album");
		albumb.setPrefSize(70, 40);
		Button movieb = new Button("Movie");
		movieb.setPrefSize(70, 40);
		Button gameb = new Button("Game");
		gameb.setPrefSize(70, 40);
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER);
		backb.setOnAction(e -> {
			window.setScene(sceneMed);
			window.show();
		});
		albumb.setOnAction(e -> {
			window.setScene(addAlbum());
			window.show();
		});
		movieb.setOnAction(e -> {
			window.setScene(addMovie());
			window.show();

		});
		gameb.setOnAction(e -> {
			window.setScene(addGame());
			window.show();
		});
		VBox vb = new VBox(20, albumb, movieb, gameb, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene addAlbum() {
		Label codeL = new Label("Code");
		Label nameL = new Label("Name");
		Label nocL = new Label("Number of copies");
		Label artistL = new Label("Artist");
		Label songsL = new Label("songs (separated by ',' )");
		TextField codetf = new TextField();
		TextField nametf = new TextField();
		TextField noctf = new TextField();
		TextField artisttf = new TextField();
		TextField songstf = new TextField();
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		ImageView addicon = new ImageView(new Image("69355-200.png"));
		addicon.setFitHeight(30);
		addicon.setFitWidth(30);
		Button addb = new Button();addb.setGraphic(addicon);
		backb.setOnAction(e -> {
			window.setScene(addMedia());
			window.show();
		});
		addb.setOnAction(e -> {
			try {
				m.addAlbum(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
						Integer.parseInt(noctf.getText().trim()), artisttf.getText(), songstf.getText());
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}

			codetf.setText("");
			nametf.setText("");
			noctf.setText("");
			artisttf.setText("");
			songstf.setText("");
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.add(codeL, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(nameL, 0, 1);
		gp.add(nametf, 2, 1);
		gp.add(nocL, 0, 2);
		gp.add(noctf, 2, 2);
		gp.add(artistL, 0, 3);
		gp.add(artisttf, 2, 3);
		gp.add(songsL, 0, 4);
		gp.add(songstf, 2, 4);
		gp.add(addb, 2, 5);
		gp.add(backb, 5, 5);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene addGame() {
		Label codeL = new Label("Code");
		Label nameL = new Label("Title");
		Label nocL = new Label("Number of copies");
		Label weightL = new Label("Weight");
		TextField codetf = new TextField();
		TextField nametf = new TextField();
		TextField noctf = new TextField();
		TextField weighttf = new TextField();
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		ImageView addicon = new ImageView(new Image("69355-200.png"));
		addicon.setFitHeight(30);
		addicon.setFitWidth(30);
		Button addb = new Button();addb.setGraphic(addicon);
		backb.setOnAction(e -> {
			window.setScene(addMedia());
			window.show();
		});
		addb.setOnAction(e -> {
			try {
				m.addGame(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
						Integer.parseInt(noctf.getText().trim()), Double.parseDouble(weighttf.getText().trim()));
				m.exit();
			} catch (Exception e2) {
				System.out.println("Error!");
			}
			codetf.setText("");
			nametf.setText("");
			noctf.setText("");
			weighttf.setText("");
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.add(codeL, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(nameL, 0, 1);
		gp.add(nametf, 2, 1);
		gp.add(nocL, 0, 2);
		gp.add(noctf, 2, 2);
		gp.add(weightL, 0, 3);
		gp.add(weighttf, 2, 3);
		gp.add(backb, 5, 4);
		gp.add(addb, 2, 4);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene addMovie() {
		Label codeL = new Label("Code");
		Label nameL = new Label("Title");
		Label nocL = new Label("Numbder of copies");
		TextField codetf = new TextField();
		TextField nametf = new TextField();
		TextField noctf = new TextField();
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		ImageView addicon = new ImageView(new Image("69355-200.png"));
		addicon.setFitHeight(30);
		addicon.setFitWidth(30);
		Button addb = new Button();addb.setGraphic(addicon);
		ToggleGroup tg = new ToggleGroup();
		RadioButton drrb = new RadioButton("DR");
		drrb.setToggleGroup(tg);
		drrb.setSelected(true);
		RadioButton acrb = new RadioButton("AC");
		acrb.setToggleGroup(tg);
		RadioButton hrrb = new RadioButton("HR");
		hrrb.setToggleGroup(tg);
		addb.setOnAction(e -> {

			try {
				if (hrrb.isSelected())
					m.addMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "HR");
				else if (acrb.isSelected())
					m.addMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "AC");
				else
					m.addMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "DR");
			} catch (Exception e2) {
				System.out.println("error!");
			}

			codetf.setText("");
			nametf.setText("");
			noctf.setText("");
			m.exit();
		});
		backb.setOnAction(e -> {
			window.setScene(addMedia());
			window.show();
		});
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.add(codeL, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(nameL, 0, 1);
		gp.add(nametf, 2, 1);
		gp.add(nocL, 0, 2);
		gp.add(noctf, 2, 2);
		gp.add(drrb, 0, 3);
		gp.add(hrrb, 2, 3);
		gp.add(acrb, 4, 3);
		gp.add(addb, 2, 4);
		gp.add(backb, 5, 4);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene deleteMedia() {
		Label idl = new Label("enter the code");
		TextField idtf = new TextField();
		HBox idhb = new HBox(idl, idtf);
		idhb.setAlignment(Pos.CENTER);
		idhb.setSpacing(40);
		Button disb = new Button("Display");
		Label infotf = new Label("info");
		disb.setOnAction(e -> {
			try {
				infotf.setText(m.searchMedia(Integer.parseInt(idtf.getText().trim())));
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		ImageView deleteicon = new ImageView(new Image("delete.png"));
		deleteicon.setFitHeight(30);
		deleteicon.setFitWidth(30);
		Button deleteb = new Button();deleteb.setGraphic(deleteicon);
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		HBox hb = new HBox(deleteb, backb);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(400);
		deleteb.setOnAction(e -> {
			try {
				m.deleteMedia(Integer.parseInt(idtf.getText().trim()));
				idtf.setText("");
				infotf.setText("info");
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		backb.setOnAction(e -> {
			window.setScene(sceneMed);
			window.show();
		});
		VBox vb = new VBox(idhb, disb, infotf, hb);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);

		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene searchMedia() {
		Label idl = new Label("enter the code");
		TextField idtf = new TextField();
		HBox hb = new HBox(20, idl, idtf);
		hb.setAlignment(Pos.CENTER);
		Button disb = new Button("Display");
		Label infol = new Label("info");
		disb.setOnAction(e -> {
			try {
				infol.setText(m.searchMedia(Integer.parseInt(idtf.getText().trim())));
			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setAlignment(Pos.BASELINE_LEFT);
		backb.setOnAction(e -> {
			window.setScene(sceneMed);
			window.show();
		});
		VBox vb = new VBox(20, hb, disb, infol, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene printInfoMedia() {

		TextArea l = new TextArea(m.getAllMediaInfo());
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setAlignment(Pos.BASELINE_LEFT);
		backb.setOnAction(e -> {
			window.setScene(sceneMed);
			window.show();
		});
		VBox vb = new VBox(20, l, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		return scene;
	}

	public static Scene updateMedia() {

		Button albumb = new Button("Album");
		albumb.setPrefSize(70, 40);
		Button movieb = new Button("Movie");
		movieb.setPrefSize(70, 40);
		Button gameb = new Button("Game");
		gameb.setPrefSize(70, 40);
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		GridPane gp = new GridPane();
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER);
		backb.setOnAction(e -> {
			window.setScene(sceneMed);
			window.show();
		});
		albumb.setOnAction(e -> {
			window.setScene(updateAlbum());
			window.show();
		});
		movieb.setOnAction(e -> {
			window.setScene(updateMovie());
			window.show();

		});
		gameb.setOnAction(e -> {
			window.setScene(updateGame());
			window.show();
		});
		VBox vb = new VBox(20, albumb, movieb, gameb, backb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene updateAlbum() {

		Label codel = new Label("code");
		TextField codetf = new TextField();
		Button disb = new Button("search");
		HBox codehb = new HBox(20, codel, codetf, disb);

		Label namel = new Label("Title");
		Label nnamel = new Label("new Ttile");
		TextField nametf = new TextField();
		HBox namehb = new HBox(30, namel, nnamel, nametf);

		Label nocl = new Label("Number of copies");
		Label nnocl = new Label("new number");
		TextField noctf = new TextField();
		HBox nochb = new HBox(30, nocl, nnocl, noctf);

		Label artistl = new Label("Artist");
		Label nartistl = new Label("new Artist");
		TextField artisttf = new TextField();
		HBox artisthb = new HBox(30, namel, nnamel, nametf);

		Label songsl = new Label("Songs(separated by ',' )");
		Label nsongsl = new Label("new songs");
		TextField songstf = new TextField();
		HBox songshb = new HBox(20, songsl, nsongsl, songstf);

		Button upb = new Button("update");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);

		backb.setOnAction(e -> {
			window.setScene(updateMedia());
			window.show();
		});
		upb.setOnAction(e -> {
			try {
				m.updateAlbum(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
						Integer.parseInt(noctf.getText().trim()), artisttf.getText(), songstf.getText());
			} catch (Exception e2) {
				System.out.println("Error!");
			}

			codetf.setText("");
			noctf.setText("");
			nametf.setText("");
			artisttf.setText("");
			songstf.setText("");
			nocl.setText("nomber of copies");
			namel.setText("title");
			artistl.setText("Artist");
			songsl.setText("Songs(separated by ',' )");
			m.exit();
		});
		disb.setOnAction(e -> {
			try {
				Album al = (Album) m.media(Integer.parseInt(codetf.getText().trim()));
				if (al != null) {
					namel.setText(al.getTitle());
					nocl.setText(Integer.valueOf(al.getNomberOfCopies()).toString());
					songsl.setText(al.getSongs());
					artistl.setText(al.getArtist());
				}

			} catch (Exception e2) {
				System.out.println("error!");
			}
		});
		HBox bhb = new HBox(200, upb, backb);

		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(20);
		gp.add(codel, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(disb, 4, 0);
		gp.add(nnamel, 2, 1);
		gp.add(namel, 0, 1);
		gp.add(nametf, 4, 1);
		gp.add(nocl, 0, 2);
		gp.add(nnocl, 2, 2);
		gp.add(noctf, 4, 2);
		gp.add(artistl, 0, 3);
		gp.add(nartistl, 2, 3);
		gp.add(artisttf, 4, 3);
		gp.add(songsl, 0, 4);
		gp.add(nsongsl, 2, 4);
		gp.add(songstf, 4, 4);
		gp.add(upb, 0, 5);
		gp.add(backb, 4, 5);
		gp.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene updateMovie() {

		Label codel = new Label("code");
		TextField codetf = new TextField();
		Button disb = new Button("search");
		HBox codehb = new HBox(20, codel, codetf, disb);

		Label namel = new Label("Title");
		Label nnamel = new Label("new Title");
		TextField nametf = new TextField();
		HBox namehb = new HBox(30, namel, nnamel, nametf);

		Label nocl = new Label("Number of copies");
		Label nnocl = new Label("new number");
		TextField noctf = new TextField();
		HBox nochb = new HBox(30, nocl, nnocl, noctf);

		Button upb = new Button("update");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);

		backb.setOnAction(e -> {
			window.setScene(updateMedia());
			window.show();
		});
		ToggleGroup tg = new ToggleGroup();
		RadioButton drrb = new RadioButton("DR");
		drrb.setToggleGroup(tg);
		drrb.setSelected(true);
		RadioButton acrb = new RadioButton("AC");
		acrb.setToggleGroup(tg);
		RadioButton hrrb = new RadioButton("HR");
		hrrb.setToggleGroup(tg);
		upb.setOnAction(e -> {
			try {
				if (hrrb.isSelected())
					m.updateMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "HR");
				else if (acrb.isSelected())
					m.updateMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "AC");
				else
					m.updateMovie(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
							Integer.parseInt(noctf.getText().trim()), "DR");
			} catch (Exception e2) {
				System.out.println("Error!");
			}

			codetf.setText("");
			nametf.setText("");
			noctf.setText("");
			namel.setText("Title");
			nocl.setText("nomber of copies");
			m.exit();

		});
		disb.setOnAction(e -> {
			try {
				Movie mov = (Movie) m.media(Integer.parseInt(codetf.getText().trim()));
				namel.setText(mov.getTitle());
				nocl.setText(Integer.valueOf(mov.getNomberOfCopies()).toString());

			} catch (Exception e2) {
				System.out.println("not integer");
			}

		});
		HBox bhb = new HBox(200, upb, backb);

		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(20);
		gp.add(codel, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(disb, 4, 0);
		gp.add(nnamel, 2, 1);
		gp.add(namel, 0, 1);
		gp.add(nametf, 4, 1);
		gp.add(nocl, 0, 2);
		gp.add(nnocl, 2, 2);
		gp.add(noctf, 4, 2);
		gp.add(hrrb, 0, 3);
		gp.add(acrb, 2, 3);
		gp.add(drrb, 4, 3);
		gp.add(upb, 0, 4);
		gp.add(backb, 4, 4);
		gp.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gp, 1000, 500);
		window.setMaximized(true);
		return scene;
	}

	public static Scene updateGame() {

		Label codel = new Label("code");
		TextField codetf = new TextField();
		Button disb = new Button("search");
		HBox codehb = new HBox(20, codel, codetf, disb);

		Label namel = new Label("Title");
		Label nnamel = new Label("new Title");
		TextField nametf = new TextField();
		HBox namehb = new HBox(30, namel, nnamel, nametf);

		Label nocl = new Label("Number of copies");
		Label nnocl = new Label("new number");
		TextField noctf = new TextField();
		HBox nochb = new HBox(30, nocl, nnocl, noctf);

		Label weightl = new Label("Wieght");
		Label nweightl = new Label("new weight");
		TextField weightf = new TextField();
		HBox weighthb = new HBox(30, nocl, nnocl, noctf);

		Button upb = new Button("update");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setOnAction(e -> {
			window.setScene(updateMedia());
			window.show();
		});

		upb.setOnAction(e -> {

			try {
				m.updateGame(Integer.parseInt(codetf.getText().trim()), nametf.getText(),
						Integer.parseInt(noctf.getText().trim()), Double.parseDouble(weightf.getText().trim()));
			} catch (Exception e2) {
				System.out.println("error!");
			}

			codetf.setText("");
			nametf.setText("");
			noctf.setText("");
			weightf.setText("");
			namel.setText("Title");
			nocl.setText("nomber of copies");
			weightl.setText("Weight");
			m.exit();

		});
		disb.setOnAction(e -> {
			try {
				Game mov = (Game) m.media(Integer.parseInt(codetf.getText().trim()));
				namel.setText(mov.getTitle());
				nocl.setText(Integer.valueOf(mov.getNomberOfCopies()).toString());
				weightl.setText(Double.valueOf(mov.getWieght()).toString());

			} catch (Exception e2) {
				System.out.println("error!");
			}

		});
		HBox bhb = new HBox(200, upb, backb);

		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(20);
		gp.add(codel, 0, 0);
		gp.add(codetf, 2, 0);
		gp.add(disb, 4, 0);
		gp.add(nnamel, 2, 1);
		gp.add(namel, 0, 1);
		gp.add(nametf, 4, 1);
		gp.add(nocl, 0, 2);
		gp.add(nnocl, 2, 2);
		gp.add(noctf, 4, 2);
		gp.add(weightl, 0, 3);
		gp.add(nweightl, 2, 3);
		gp.add(weightf, 4, 3);
		gp.add(upb, 0, 4);
		gp.add(backb, 4, 4);
		gp.setAlignment(Pos.CENTER);
		window.setMaximized(true);
		Scene scene = new Scene(gp, 1000, 500);
		return scene;
	}

	public static Scene rent1() {
		Label l = new Label("put the limit for limited customers");
		TextField tf = new TextField();
		tf.setPrefColumnCount(10);
		HBox limithb = new HBox(20, l, tf);
		limithb.setAlignment(Pos.CENTER);
		Button b = new Button("add");
		b.setOnAction(e -> {
			try {
				m.setLimitedPlanLimit(Integer.parseInt(tf.getText().trim()));
				window.setScene(rent2());
				window.setMaximized(true);
				window.show();
			} catch (Exception e1) {
				System.out.println("error!");
			}
		});
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		backb.setOnAction(e -> {
			window.setScene(scene1);
			window.show();
		});
		HBox hb = new HBox(200, b, backb);
		hb.setAlignment(Pos.CENTER);
		VBox vb = new VBox(20, limithb, hb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		return scene;
	}

	public static Scene rent2() {
		Label idl = new Label("customer id");
		TextField idtf = new TextField();
		Button idb = new Button("display");
		HBox idhb = new HBox(20, idl, idtf, idb);
		idhb.setAlignment(Pos.CENTER);
		Label idinfo = new Label();
		Label codel = new Label("media code");
		TextField codetf = new TextField();
		Button codeb = new Button("display");
		HBox codehb = new HBox(20, codel, codetf, codeb);
		codehb.setAlignment(Pos.CENTER);
		Label codeinfo = new Label();
		Label date = new Label("rent date");
		TextField datetf = new TextField();
		HBox datehb = new HBox(20, date, datetf);
		datehb.setAlignment(Pos.CENTER);
		Button add = new Button("add to cart");
		Button pro = new Button("procces request");
		ImageView backicon = new ImageView(new Image("back-1667947-2039464.png"));
		backicon.setFitHeight(30);
		backicon.setFitWidth(30);
		Button backb = new Button();
		backb.setGraphic(backicon);
		HBox bhb = new HBox(20, add, pro, backb);
		bhb.setAlignment(Pos.CENTER);
		Label prol = new Label();
		idb.setOnAction(e -> {
			try {
				idinfo.setText(m.searchCustomer(Integer.parseInt(idtf.getText().trim())));
				prol.setText("");
				datetf.setText("");
			} catch (Exception e2) {
				System.out.println("error!");
			}
		});
		codeb.setOnAction(e -> {
			try {
				codeinfo.setText(m.searchMedia(Integer.parseInt(codetf.getText().trim())));
				prol.setText("");
				datetf.setText("");
			} catch (Exception e2) {
				System.out.println("error!");
			}
		});
		add.setOnAction(e -> {
			try {
				m.addToCart(Integer.parseInt(idtf.getText().trim()), Integer.parseInt(codetf.getText().trim()));
				m.exit();
				prol.setText("");
			} catch (Exception e2) {
				System.out.println("error!");
			}
		});
		pro.setOnAction(e -> {
			try {
				prol.setText(m.processRequests1(Integer.parseInt(idtf.getText().trim()),
						Integer.parseInt(codetf.getText().trim())));
				datetf.setText("");
				codetf.setText("");
				idtf.setText("");
				codeinfo.setText("");
				idinfo.setText("");
				m.exit();
			} catch (Exception e2) {
				System.out.println("error!");
			}
		});
		backb.setOnAction(e -> {
			window.setScene(rent1());
			window.show();
		});
		VBox vb = new VBox(20, idhb, idinfo, codehb, codeinfo, datehb, prol, bhb);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 1000, 500);
		return scene;
	}

}
