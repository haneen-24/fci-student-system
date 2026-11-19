package sample.view;                                              // package الـ views

import javafx.geometry.Insets;                                    // للمسافات الداخلية (padding)
import javafx.geometry.Pos;                                       // لمحاذاة العناصر
import javafx.scene.Scene;                                        // المشهد اللي بيتحط جوه النافذة
import javafx.scene.control.Button;                               // الزراير
import javafx.scene.control.Label;                                // النصوص الثابتة
import javafx.scene.control.TableColumn;                          // عمود في الجدول
import javafx.scene.control.TableView;                            // الجدول نفسه
import javafx.scene.control.TextField;                            // خانة الكتابة
import javafx.scene.control.cell.PropertyValueFactory;            // بيربط العمود بـ getter في Course
import javafx.scene.layout.GridPane;                              // ترتيب على شكل شبكة (للفورم)
import javafx.scene.layout.HBox;                                  // ترتيب أفقي
import javafx.scene.layout.Priority;                              // أولوية أخذ المساحة
import javafx.scene.layout.VBox;                                  // ترتيب رأسي
import javafx.stage.Modality;                                     // نوع النافذة (مانعة للي وراها)
import javafx.stage.Stage;                                        // النافذة

import sample.model.Course;                                       // الـ model بتاع المادة

public class CourseView {                                         // الشكل بس (S من SOLID)

    private final VBox root;                                      // الحاوية الرئيسية للصفحة
    private TextField searchField;                                // خانة البحث
    private TableView<Course> courseTable;                        // جدول المواد
    private Button addButton;                                     // زرار الإضافة
    private Button editButton;                                    // زرار التعديل

    public CourseView() {                                         // الـ constructor: بيبني الصفحة
        root = new VBox(20);                                      // حاوية رأسية والمسافة بين العناصر 20
        root.setPadding(new Insets(30));                          // مسافة 30 من كل الجوانب

        Label title = new Label("Courses");                       // عنوان الصفحة
        Label description = new Label("Search and manage courses");  // وصف صغير تحت العنوان
        VBox titleSection = new VBox(5, title, description);      // اجمعهم فوق بعض بمسافة 5

        root.getChildren().addAll(                                // ضيف الأجزاء للصفحة بالترتيب:
                titleSection,                                     // 1) العنوان
                createSearchSection(),                            // 2) البحث + الزراير
                createCourseTable()                               // 3) الجدول
        );

        VBox.setVgrow(courseTable, Priority.ALWAYS);              // الجدول ياخد باقي المساحة المتاحة
    }

    private HBox createSearchSection() {                          // بيبني صف البحث والزراير
        searchField = new TextField();                            // اعمل خانة البحث
        searchField.setPromptText("Search by name or code...");   // النص الرمادي جواها
        searchField.setPrefHeight(40);                            // الارتفاع

        addButton = new Button("➕ Add Course");                  // زرار الإضافة
        editButton = new Button("✏ Edit Course");                 // زرار التعديل
        addButton.setPrefHeight(40);                              // نفس ارتفاع خانة البحث
        editButton.setPrefHeight(40);                             // نفس الارتفاع

        HBox box = new HBox(10, searchField, addButton, editButton);  // صف أفقي فيه التلاتة بمسافة 10
        box.setAlignment(Pos.CENTER_LEFT);                        // محاذاة لليسار ومنتصف رأسياً
        HBox.setHgrow(searchField, Priority.ALWAYS);              // خانة البحث ياخد العرض الفاضي
        return box;                                               // رجّع الصف
    }

    private TableView<Course> createCourseTable() {               // بيبني الجدول
        courseTable = new TableView<>();                          // جدول فاضي بيعرض Course

        TableColumn<Course, String> codeColumn =                  // عمود الكود
                new TableColumn<>("Code");                        // عنوان العمود
        codeColumn.setCellValueFactory(                           // قوله منين يجيب القيمة
                new PropertyValueFactory<>("code"));              // بينادي getCode()

        TableColumn<Course, String> nameColumn =                  // عمود الاسم
                new TableColumn<>("Course Name");                 // عنوان العمود
        nameColumn.setCellValueFactory(                           // منين يجيب القيمة
                new PropertyValueFactory<>("name"));              // بينادي getName()

        TableColumn<Course, Integer> hoursColumn =                // عمود الساعات
                new TableColumn<>("Credit Hours");                // عنوان العمود
        hoursColumn.setCellValueFactory(                          // منين يجيب القيمة
                new PropertyValueFactory<>("creditHours"));       // بينادي getCreditHours()

        courseTable.getColumns().addAll(                          // ضيف الأعمدة للجدول
                codeColumn, nameColumn, hoursColumn);
        courseTable.setColumnResizePolicy(                        // خلي الأعمدة تملا عرض الجدول
                TableView.CONSTRAINED_RESIZE_POLICY);
        return courseTable;                                       // رجّع الجدول
    }

    public VBox getRoot() {                                       // الصفحة كلها (MainController هيعرضها في النص)
        return root;
    }

    public TextField getSearchField() {                           // الـ Controller محتاجها عشان يسمع للبحث
        return searchField;
    }

    public TableView<Course> getCourseTable() {                   // الـ Controller هيحط فيها البيانات
        return courseTable;
    }

    public Button getAddButton() {                                // الـ Controller هيربط الضغطة
        return addButton;
    }

    public Button getEditButton() {                               // الـ Controller هيربط الضغطة
        return editButton;
    }

    // =====================================================================
    // نافذة الإضافة/التعديل (نافذة واحدة للاتنين)
    // =====================================================================

    public static class CourseForm {                              // كلاس داخلي، عشان نفضل في 5 ملفات

        private final Stage stage;                                // نافذة الفورم
        private final TextField codeField;                        // خانة الكود
        private final TextField nameField;                        // خانة الاسم
        private final TextField hoursField;                       // خانة الساعات
        private final Button saveButton;                          // زرار الحفظ
        private final Button cancelButton;                        // زرار الإلغاء

        public CourseForm(String windowTitle, Course course) {    // course = null يعني إضافة، غير كده تعديل
            stage = new Stage();                                  // اعمل نافذة جديدة
            stage.setTitle(windowTitle);                          // عنوانها
            stage.initModality(Modality.APPLICATION_MODAL);       // امنع الضغط على الشاشة اللي ورا لحد ما تتقفل

            codeField = new TextField();                          // خانة الكود فاضية
            nameField = new TextField();                          // خانة الاسم فاضية
            hoursField = new TextField();                         // خانة الساعات فاضية
            codeField.setPromptText("e.g. CS101");                // مثال للمستخدم
            nameField.setPromptText("e.g. Programming 1");        // مثال للمستخدم
            hoursField.setPromptText("e.g. 3");                   // مثال للمستخدم

            if (course != null) {                                 // لو ده تعديل (فيه مادة جاية)
                codeField.setText(course.getCode());              // املا الكود القديم
                nameField.setText(course.getName());              // املا الاسم القديم
                hoursField.setText(                               // املا الساعات القديمة
                        String.valueOf(course.getCreditHours())); // حوّل الرقم لنص
            }

            GridPane form = new GridPane();                       // شبكة للفورم
            form.setHgap(15);                                     // مسافة أفقية بين الأعمدة
            form.setVgap(15);                                     // مسافة رأسية بين الصفوف
            form.add(new Label("Course Code"), 0, 0);             // عمود 0 صف 0
            form.add(codeField, 1, 0);                            // عمود 1 صف 0
            form.add(new Label("Course Name"), 0, 1);             // عمود 0 صف 1
            form.add(nameField, 1, 1);                            // عمود 1 صف 1
            form.add(new Label("Credit Hours"), 0, 2);            // عمود 0 صف 2
            form.add(hoursField, 1, 2);                           // عمود 1 صف 2

            saveButton = new Button("Save");                      // زرار الحفظ
            cancelButton = new Button("Cancel");                  // زرار الإلغاء
            HBox buttons = new HBox(10, saveButton, cancelButton);// الزراير جنب بعض
            buttons.setAlignment(Pos.CENTER_RIGHT);               // على اليمين

            VBox formRoot = new VBox(20, new Label(windowTitle),  // العنوان + الفورم + الزراير فوق بعض
                    form, buttons);
            formRoot.setPadding(new Insets(25));                  // مسافة من الجوانب

            stage.setScene(new Scene(formRoot, 420, 280));        // حط المحتوى في النافذة بمقاس 420x280
        }

        public TextField getCodeField() {                         // getter للكود
            return codeField;
        }

        public TextField getNameField() {                         // getter للاسم
            return nameField;
        }

        public TextField getHoursField() {                        // getter للساعات
            return hoursField;
        }

        public Button getSaveButton() {                           // getter لزرار الحفظ
            return saveButton;
        }

        public Button getCancelButton() {                         // getter لزرار الإلغاء
            return cancelButton;
        }

        public Stage getStage() {                                 // getter للنافذة (عشان الـ Controller يقفلها)
            return stage;
        }

        public void show() {                                      // اعرض النافذة
            stage.showAndWait();                                  // وانتظر لحد ما تتقفل
        }
    }
}
