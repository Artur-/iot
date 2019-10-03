package org.vaadin.artur.microfront.bundle;

import org.artur.iot.component.JCard;
import org.artur.iot.component.PaperTooltip;
import org.vaadin.artur.github_corner.GitHubCorner;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.cookieconsent.CookieConsent;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.ironlist.IronList;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;

// Must have @Route for Flow to scan it
@Route("")
public class Bundle {
    private Crud crud = new Crud();
    private Icon icons = new Icon();
    private Grid grid = new Grid();
    private SplitLayout splitlayout = new SplitLayout();
    private CookieConsent cookieconsent = new CookieConsent();
    private ComboBox combobox = new ComboBox();
    private Upload upload = new Upload();
    private Dialog dialog = new Dialog();
    private Select select = new Select();
    private AppLayout applayout = new AppLayout();
    private DrawerToggle toggle = new DrawerToggle();
    private Board board = new Board();
    private Notification notification = new Notification();
    private Chart charts = new Chart();
    private GridPro gridpro = new GridPro();
    private ProgressBar progressbar = new ProgressBar();
    private HorizontalLayout horizontallayout = new HorizontalLayout();
    private VerticalLayout verticallayout = new VerticalLayout();
    private LoginOverlay login = new LoginOverlay();
    private Button button = new Button();
    private DatePicker datepicker = new DatePicker();
    private TextField textfield = new TextField();
    private MenuBar menubar = new MenuBar();
    private GitHubCorner gitHubCorner = new GitHubCorner("foo");
    /*
    private CustomField customfield = new CustomField(){

        @Override
        protected Object generateModelValue() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        protected void setPresentationValue(Object newPresentationValue) {
            // TODO Auto-generated method stub

        }
    };
    */
    private FormLayout formlayout = new FormLayout();
    private Accordion accordion = new Accordion();
    private ConfirmDialog confirmdialog = new ConfirmDialog();
    private IronList ironlist = new IronList();
    private ListBox listbox = new ListBox();
    private Checkbox checkbox = new Checkbox();
    private Details details = new Details();
    private TimePicker timepicker = new TimePicker();
    private ContextMenu contextmenu = new ContextMenu();
    private Tabs tabs = new Tabs();
    private RadioButtonGroup radiobutton = new RadioButtonGroup();
    private RichTextEditor richtexteditor = new RichTextEditor();
    
    private PaperTooltip tooltip = new PaperTooltip();
    private JCard card = new JCard("");
}