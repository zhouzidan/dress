package com.dudress.dress.db;

import android.content.Context;
import android.text.TextUtils;

import com.dudress.dress.DressApplication;
import com.dudress.dress.db.models.Account;

import java.util.List;

/**
 * Created by zhou on 16-4-7.
 */
public class AccountDBManager {
    private Context context;
    private static AccountDBManager instance;
    private AccountDBManager(){
        context = DressApplication.getAppContext();
    }
    public static AccountDBManager get(){
        if (instance == null)
            instance = new AccountDBManager();
        return instance;
    }

    public static int ACCOUNT_STATUS_ON = 1 ;
    public static int ACCOUNT_STATUS_OFF = 2 ;


    public void deleteByEmail(String email){
        if (TextUtils.isEmpty(email) == false){
            List<Account> accounts = Account.find(Account.class,"email = ? ", new String[]{email});
            if (accounts.size() > 0){
                for (Account account:accounts) {
                    account.delete();
                }
            }
        }
    }

    public void deleteByUserId(String userId){
        if (TextUtils.isEmpty(userId) == false){
            List<Account> accounts = Account.find(Account.class,"userid = ? ", new String[]{userId});
            if (accounts.size() > 0){
                for (Account account:accounts) {
                    account.delete();
                }
            }
        }
    }



}
