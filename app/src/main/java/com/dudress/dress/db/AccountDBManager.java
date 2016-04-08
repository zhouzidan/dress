package com.dudress.dress.db;

import android.content.Context;
import android.text.TextUtils;

import com.dudress.dress.DressApplication;
import com.dudress.dress.db.models.Account;

import org.w3c.dom.Text;

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

    private String userId = null;

    public String getUserId() {
        if (userId == null){
            userId = getUserIdFromDB();
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String getUserIdFromDB(){
        List<Account> accounts = Account.find(Account.class," status = ? ",new String[]{String.valueOf(ACCOUNT_STATUS_ON)});
        if (accounts.size() > 0)
            return accounts.get(0).getPicUrl();
        return null;
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
            List<Account> accounts = Account.find(Account.class," userid = ? ", new String[]{userId});
            if (accounts.size() > 0){
                for (Account account:accounts) {
                    account.delete();
                }
            }
        }
    }

    public String getPicUrl(){
        String picurl = "http://img4.duitang.com/uploads/item/201602/29/20160229121022_rFktZ.jpeg";
        String userId = getUserId();
        if (TextUtils.isEmpty(userId) == false){
            List<Account> accounts = Account.find(Account.class," userid = ? AND status = ? ",new String[]{ userId ,String.valueOf(ACCOUNT_STATUS_ON)});
            if (accounts.size() > 0)
                return accounts.get(0).getPicUrl();
        }
        return picurl;
    }

    /**
     * 账户注销
     */
    public void logout(){
        List<Account> accounts = Account.find(Account.class," userid = ? AND status = ? ",new String[]{userId,String.valueOf(ACCOUNT_STATUS_ON)});
        for (Account account:accounts) {
            account.setStatus(ACCOUNT_STATUS_OFF);
            account.save();
        }
        setUserId(null);
    }

}
