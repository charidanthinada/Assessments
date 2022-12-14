package com.chari.assessment.di.module;




import android.app.Application;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.chari.assessment.App;
import com.chari.assessment.repositories.AppRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // TODO: Production v-5-6 , UAT v-1-2

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
           /* database.execSQL("ALTER TABLE DuplicateDynamicUITable "
                    +"ADD COLUMN ProductId TEXT");
            database.execSQL("ALTER TABLE DuplicateDynamicUITable "
                    +"ADD COLUMN UniqueId TEXT");*/
            /*database.execSQL("ALTER TABLE DynamicUITable "
                    +"ADD COLUMN UniqueId TEXT");
            database.execSQL("ALTER TABLE RawDataTable "
                    +"ADD COLUMN UniqueId TEXT");*/
           /* database.execSQL("CREATE TABLE IF NOT EXISTS ProductMasterTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ProductCode TEXT," +
                    "FinacleCode TEXT,ProductCategory TEXT,SchemeName TEXT,MaxLoanAmount TEXT,MinLoanAmount TEXT,MinROI TEXT,MaxROI TEXT," +
                    "DefaultROI TEXT,MinTenor TEXT,MaxTenor TEXT,ProcessingFee TEXT,InsuranceFee TEXT,Step TEXT,DBRCalMtd TEXT," +
                    "FeeAmt TEXT,Percentage TEXT,GST TEXT,LTVCalMtd TEXT)");*/

            // TODO: Migration 7 to 8
           /* database.execSQL("CREATE TABLE IF NOT EXISTS EkycAttemptTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,aadhaar_no TEXT," +
                    "attempt INTEGER NOT NULL,screen_no TEXT,message TEXT)");*/


           /* // TODO: Migration 8 to 9
             database.execSQL("CREATE TABLE IF NOT EXISTS DocumentMasterTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ScreenId TEXT," +
                    "CustomerType TEXT,DisplayName TEXT,DocumentName TEXT,TagName TEXT,FileFormat TEXT)");*/

     /*       // TODO: Migration 9 to 10
            database.execSQL("CREATE TABLE IF NOT EXISTS DocumentUploadTableNew (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,screen_id TEXT," +
                    "client_id TEXT,document_name TEXT,document_sub_name TEXT,document_tag TEXT,customer_type TEXT,display_name TEXT" +
                    ",file_name TEXT,position TEXT,document_status INTEGER,loan_type TEXT,module_type TEXT,file_path TEXT)");*/


            // TODO: 08-01-2020 Migration 1 to 2
           /* database.execSQL("ALTER TABLE SubmitDataTable "
                    +"ADD COLUMN IMEINumber TEXT");
            database.execSQL("ALTER TABLE SubmitDataTable "
                    +"ADD COLUMN request TEXT");*/

            // TODO: 13-01-2020  Migration 2 to 3
          /*  database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    +"ADD COLUMN secretKey BLOB");
            database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    +"ADD COLUMN randomNumber BLOB");*/

          /*  // TODO: 20-01-2020  Migration 3 to 4
            database.execSQL("ALTER TABLE OTPVerificationTable "
                    +"ADD COLUMN isOTPGenerated INTEGER NOT NULL DEFAULT 0");*/

            // TODO: 06-02-2020  Migration 4 to 5
//            database.execSQL("CREATE TABLE IF NOT EXISTS LogTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,date_time TEXT," +
//                    "staff_id TEXT,methodName TEXT,message TEXT,screen_no TEXT,screen_name TEXT,client_id TEXT,loan_type TEXT," +
//                    "module_type TEXT,imei_number TEXT)");
//            // TODO: 15-02-2020  Migration 5 to 6
//            database.execSQL("ALTER TABLE DocumentUploadTableNew "
//                    +"ADD COLUMN FILES TEXT");



        }
    };

    // --- DATABASE INJECTION ---






    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }



    @Provides
    Context provideAppContext() {
        return App.context;
    }

    @Provides
    @Singleton
    AppRepository provideBootStrapRepository(Executor executor, Context context) {
        return new AppRepository( executor,context);
    }


}
