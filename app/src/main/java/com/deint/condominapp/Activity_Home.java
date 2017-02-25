package com.deint.condominapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.deint.condominapp.fragments.Home;
import com.deint.condominapp.fragments.form.Form_Board;
import com.deint.condominapp.fragments.form.Form_CBoard;
import com.deint.condominapp.fragments.form.Form_Diary;
import com.deint.condominapp.fragments.form.Form_Document;
import com.deint.condominapp.fragments.form.Form_Incident;
import com.deint.condominapp.fragments.form.Form_Meeting;
import com.deint.condominapp.fragments.list.List_Board;
import com.deint.condominapp.fragments.list.List_CBoard;
import com.deint.condominapp.fragments.list.List_Community;
import com.deint.condominapp.fragments.list.List_Diary;
import com.deint.condominapp.fragments.list.List_Document;
import com.deint.condominapp.fragments.list.List_Incident;
import com.deint.condominapp.fragments.list.List_Information;
import com.deint.condominapp.fragments.list.List_Meeting;
import com.deint.condominapp.fragments.list.List_User;
import com.deint.condominapp.pojos.Pojo_Community;
import com.deint.condominapp.pojos.Pojo_Document;
import com.deint.condominapp.pojos.Pojo_Entry;
import com.deint.condominapp.pojos.Pojo_Incident;
import com.deint.condominapp.pojos.Pojo_Meeting;
import com.deint.condominapp.pojos.Pojo_Note;
import com.deint.condominapp.pojos.Pojo_User;
import com.deint.condominapp.preferences.application.Settings;

public class Activity_Home extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, Home.FragmentHomeListener,
        List_Board.FragmentListBoardListener, List_CBoard.FragmentListCBoardListener, List_Community.FragmentListCommunityListener,
        List_Diary.FragmentListDiaryListener, List_Document.FragmentListDocumentListener, List_Incident.FragmentListIncidentListener,
        List_Meeting.FragmentListMeetingListener, List_User.FragmentListUserListener,
        Form_Incident.FragmentFormIncidentListener, Form_Board.FragmentFormBoardListener, Form_CBoard.FragmentFormCBoardListener,
        Form_Diary.FragmentFormDiaryListener, Form_Document.FragmentFormDocumentListener, Form_Meeting.FragmentFormMeetingListener {

    private List_Incident list_incident;
    private List_Diary list_diary;
    private List_Board list_board;
    private List_CBoard list_cBoard;
    private List_Document list_document;
    private List_Meeting list_meeting;
    private List_Information list_information;
    private List_User list_user;
    private List_Community list_community;
    private Form_Board form_board;
    private Form_CBoard form_cBoard;
    private Form_Diary form_diary;
    private Form_Document form_document;
    private Form_Incident form_incident;
    private Form_Meeting form_meeting;

    private DrawerLayout drawerLayout;
    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);

        Toolbar homeToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(homeToolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_36dp);
        }

        list_incident = new List_Incident();
        list_diary = new List_Diary();
        list_board = new List_Board();
        list_cBoard = new List_CBoard();
        list_document = new List_Document();
        list_meeting = new List_Meeting();
        list_information = new List_Information();
        list_user = new List_User();
        list_community = new List_Community();

        setupDrawerContent();
        showHome();
    }

    /**
     * Method that show snackbar notifications to the user with different colors
     * depending on the 2nd parameter if it is an error or not
     */
    public void showSnackbar(String msg, boolean error) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_home), msg, Snackbar.LENGTH_SHORT);
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        View view = snackbar.getView();
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        textView.setGravity(Gravity.CENTER);
        if (error) {
            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorError));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSuccess));
        }
        snackbar.show();
    }

    /**
     * Method that initializes the Home fragment at the start of the Activity
     */
    public void showHome() {
        Home fragmentHome = new Home();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, fragmentHome, "list_incident");
        fragmentTransaction.commit();
    }

    //region List Interfaces
    //---------------------------------------------------------------------------------------------
    @Override
    /**
     * Method to open the incidents fragment
     */
    public void onHomeFragmentIncidentsButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_incident, "list_incident");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the notes fragment
     */
    public void onHomeFragmentDiaryButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_diary, "list_diary");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the entries fragment
     */
    public void onHomeFragmentBoardButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_board, "list_board");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the second entries fragment
     */
    public void onHomeFragmentCBoardButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_cBoard, "list_cBoard");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the documents fragment
     */
    public void onHomeFragmentDocumentsButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_document, "list_document");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the meetings fragment
     */
    public void onHomeFragmentMeetingsButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_meeting, "list_meeting");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the information fragment
     */
    public void onHomeFragmentInformationButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_information, "list_information");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the users fragment
     */
    public void onHomeFragmentUsersButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_user, "list_user");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the communities fragment
     */
    public void onHomeFragmentCommunitiesButtonTap() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, list_community, "list_community");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open the profile fragment
     */
    public void onHomeFragmentProfileButtonTap() {
        com.deint.condominapp.fragments.Profile profile = new com.deint.condominapp.fragments.Profile();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, profile, "profile");
        fragmentTransaction.commit();
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Form Interfaces
    //---------------------------------------------------------------------------------------------

    @Override
    /**
     * Method to open an empty entry form
     */
    public void onManageBoardOpen() {
        form_board = new Form_Board();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_entryf", null);
        form_board.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_board);
        fragmentTransaction.addToBackStack("form_board");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update entry form
     */
    public void onManageBoardOpen(Pojo_Entry entry) {
        form_board = new Form_Board();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_entryf", entry);
        form_board.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_board);
        fragmentTransaction.addToBackStack("form_board");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty entry form
     */
    public void onManageCBoardOpen() {
        form_cBoard = new Form_CBoard();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_entrys", null);
        form_cBoard.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_cBoard);
        fragmentTransaction.addToBackStack("form_cBoard");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update entry form
     */
    public void onManageCBoardOpen(Pojo_Entry entry) {
        form_cBoard = new Form_CBoard();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_entrys", entry);
        form_cBoard.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_cBoard);
        fragmentTransaction.addToBackStack("form_cBoard");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty community form
     */
    public void onManageCommunityOpen() {
    }

    @Override
    /**
     * Method to open an update community form
     */
    public void onManageCommunityOpen(Pojo_Community community) {
    }

    @Override
    /**
     * Method to open an empty note form
     */
    public void onManageDiaryOpen() {
        form_diary = new Form_Diary();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_note", null);
        form_diary.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_diary);
        fragmentTransaction.addToBackStack("form_diary");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update note form
     */
    public void onManageDiaryOpen(Pojo_Note note) {
        form_diary = new Form_Diary();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_note", note);
        form_diary.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_diary);
        fragmentTransaction.addToBackStack("form_diary");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty document form
     */
    public void onManageDocumentOpen() {
        form_document = new Form_Document();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_document", null);
        form_document.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_document);
        fragmentTransaction.addToBackStack("form_document");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update document form
     */
    public void onManageDocumentOpen(Pojo_Document document) {
        form_document = new Form_Document();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_document", document);
        form_document.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_document);
        fragmentTransaction.addToBackStack("form_document");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty incident form
     */
    public void onManageIncidentOpen() {
        form_incident = new Form_Incident();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_incident", null);
        form_incident.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_incident);
        fragmentTransaction.addToBackStack("form_incident");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update incident form
     */
    public void onManageIncidentOpen(Pojo_Incident incident) {
        form_incident = new Form_Incident();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_incident", incident);
        form_incident.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_incident);
        fragmentTransaction.addToBackStack("form_incident");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty meeting form
     */
    public void onManageMeetingOpen() {
        form_meeting = new Form_Meeting();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_meeting", null);
        form_meeting.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_meeting);
        fragmentTransaction.addToBackStack("form_meeting");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an update meeting form
     */
    public void onManageMeetingOpen(Pojo_Meeting meeting) {
        form_meeting = new Form_Meeting();
        Bundle bundle = new Bundle();
        bundle.putParcelable("pojo_meeting", meeting);
        form_meeting.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_home, form_meeting);
        fragmentTransaction.addToBackStack("form_meeting");
        fragmentTransaction.commit();
    }

    @Override
    /**
     * Method to open an empty user form
     */
    public void onManageUserOpen() {
    }

    @Override
    /**
     * Method to open an update user form
     */
    public void onManageUserOpen(Pojo_User user) {
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Form Click Interfaces
    //---------------------------------------------------------------------------------------------

    @Override
    /**
     * Method that sends to the incidents fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptIncident(Pojo_Incident incident, boolean update) {
        list_incident.recieveIncidentFromHome(incident, update);
    }

    @Override
    /**
     * Method that sends to the entries fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptBoard(Pojo_Entry entry, boolean update) {
        list_board.recieveEntryFromHome(entry, update);
    }

    @Override
    /**
     * Method that sends to the entries fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptCBoard(Pojo_Entry entry, boolean update) {
        list_cBoard.recieveEntryFromHome(entry, update);
    }

    @Override
    /**
     * Method that sends to the notes fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptDiary(Pojo_Note note, boolean update) {
        list_diary.recieveNoteFromHome(note, update);
    }

    @Override
    /**
     * Method that sends to the documents fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptDocument(Pojo_Document document, boolean update) {
        list_document.recieveDocumentFromHome(document, update);
    }

    @Override
    /**
     * Method that sends to the meetings fragment an element for the presenter
     * The 2nd parameter says if the element has to be added or updated
     */
    public void onAcceptMeeting(Pojo_Meeting meeting, boolean update) {

    }

    //---------------------------------------------------------------------------------------------
    //endregion

    //region Menu methods
    //---------------------------------------------------------------------------------------------

    @Override
    /**
     * Home menu options
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * NavigationDrawer implementation
     */
    private void setupDrawerContent() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    /**
     * NavigationDrawer menu options
     */
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navIncidents:
                onHomeFragmentIncidentsButtonTap();
                break;
            case R.id.navDiary:
                onHomeFragmentDiaryButtonTap();
                break;
            case R.id.navBoard:
                onHomeFragmentBoardButtonTap();
                break;
            case R.id.navCBoard:
                onHomeFragmentCBoardButtonTap();
                break;
            case R.id.navDocuments:
                onHomeFragmentDocumentsButtonTap();
                break;
            case R.id.navMeetings:
                onHomeFragmentMeetingsButtonTap();
                break;
            case R.id.navInformation:
                onHomeFragmentInformationButtonTap();
                break;
            case R.id.navProfile:
                onHomeFragmentProfileButtonTap();
                break;
            case R.id.navUsers:
                onHomeFragmentUsersButtonTap();
                break;
            case R.id.navCommunities:
                onHomeFragmentCommunitiesButtonTap();
                break;
            case R.id.twoNavShop:
                //
                break;
            case R.id.twoNavGame:
                //
                break;
            case R.id.threeNavSettings:
                Intent intent = new Intent(Activity_Home.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.threeNavHelp:
                //
                break;
            case R.id.fourNavAbout:
                break;
            default:
                item.setChecked(false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        setTitle(item.getTitle());
        clearStack();
        return true;
    }

    //---------------------------------------------------------------------------------------------
    //endregion

    @Override
    /**
     * OnBackPressed override method
     */
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0 || exit)
            super.onBackPressed();
        else {
            exit = true;
            Snackbar.make(
                    findViewById(R.id.activity_home),
                    getString(R.string.back_pressed_twice),
                    Snackbar.LENGTH_SHORT)
                    .addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            exit = false;
                        }
                    })
                    .show();
        }

    }

    /**
     * Method used in the NavigationDrawer to clear the back stack after opening an option
     */
    public void clearStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }
}