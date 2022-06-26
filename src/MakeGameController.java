import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MakeGameController {
    @FXML
    CheckBox name;
    @FXML
    CheckBox family;
    @FXML
    CheckBox city;
    @FXML
    CheckBox country;
    @FXML
    CheckBox food;
    @FXML
    CheckBox clothes;
    @FXML
    CheckBox fruit;
    @FXML
    CheckBox car;
    @FXML
    CheckBox flower;
    @FXML
    CheckBox animal;
    @FXML
    CheckBox objects;
    @FXML
    TextField time;
    @FXML
    Text text;
    @FXML
    Button back;
    static int counter = 0;
    static ArrayList<String> selected = new ArrayList<>();
    static int minute;

    public void setNumeric() {
        time.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                time.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void selectName() {
        if (name.isSelected()) {
            counter--;
            selected.remove(name.getText());
        } else {
            counter++;
            selected.add(name.getText());
        }
        System.out.println(counter);
    }

    public void selectFamily() {
        if (family.isSelected()) {
            counter--;
            selected.remove(family.getText());
        } else {
            counter++;
            selected.add(family.getText());
        }
        System.out.println(counter);
    }

    public void selectCity() {
        if (city.isSelected()) {
            counter--;
            selected.remove(city.getText());
        } else {
            counter++;
            selected.add(city.getText());
        }
        System.out.println(counter);
    }

    public void selectCountry() {
        if (country.isSelected()) {
            counter--;
            selected.remove(country.getText());
        } else {
            counter++;
            selected.add(country.getText());
        }
        System.out.println(counter);
    }

    public void selectFlower() {
        if (flower.isSelected()) {
            counter--;
            selected.remove(flower.getText());
        } else {
            counter++;
            selected.add(flower.getText());
        }
        System.out.println(counter);
    }

    public void selectFood() {
        if (food.isSelected()) {
            counter--;
            selected.remove(food.getText());
        } else {
            counter++;
            selected.add(food.getText());
        }
        System.out.println(counter);
    }

    public void selectClothes() {
        if (clothes.isSelected()) {
            counter--;
            selected.remove(clothes.getText());
        } else {
            counter++;
            selected.add(clothes.getText());
        }
        System.out.println(counter);
    }

    public void selectFruit() {
        if (fruit.isSelected()) {
            counter--;
            selected.remove(fruit.getText());
        } else {
            counter++;
            selected.add(fruit.getText());
        }
        System.out.println(counter);
    }

    public void selectObject() {
        if (objects.isSelected()) {
            counter--;
            selected.remove(objects.getText());
        } else {
            counter++;
            selected.add(objects.getText());
        }
        System.out.println(counter);
    }

    public void selectAnimal() {
        if (animal.isSelected()) {
            counter--;
            selected.remove(animal.getText());
        } else {
            counter++;
            selected.add(animal.getText());
        }
        System.out.println(counter);
    }

    public void selectCar() {
        if (car.isSelected()) {
            counter--;
            selected.remove(car.getText());
        } else {
            counter++;
            selected.add(car.getText());
        }
        System.out.println(counter);
    }

    public void make() throws Exception {
        if (counter < 5) {
            text.setText("تعداد موضوعات کمتر از 5 می باشد!!!");
            text.setFill(Color.RED);
        } else if (time.getText().equals("")) {
            text.setText("لطفا زمان بازی را مشخص کنید");
            text.setFill(Color.RED);
        } else {
            System.out.println(selected);
            minute = Integer.parseInt(time.getText());
            Main main = new Main();
            main.changeScene(selected.size() + ".fxml");
        }
    }

    public void goBack() throws Exception {
        Main main = new Main();
        main.changeScene("welcome.fxml");
    }
}
