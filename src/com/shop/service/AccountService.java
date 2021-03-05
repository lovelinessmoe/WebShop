package com.shop.service;

import com.shop.bean.AccountBean;
import com.shop.bean.PageBean;
import com.shop.dao.AccountDao;

import java.util.Scanner;

public class AccountService {
    AccountDao accountdao = new AccountDao();
    Scanner sc = new Scanner(System.in);

    public AccountBean getAccountBean(AccountBean loginbean) {
        return accountdao.getAccountBean(loginbean);
    }

    public int modifyName(AccountBean loginbean, String u_name) {
        return accountdao.modifyName(u_name, loginbean);
    }

    public int modifyPwd(AccountBean loginbean,String Pwd_new,String Pwd_old) {
        return accountdao.modifyPwd(Pwd_new, Pwd_old, loginbean);
    }

    public int modifyMobile(AccountBean loginbean, String mobile) {
        return accountdao.modifyMobile(mobile, loginbean);
    }

    public int modifyEmail(AccountBean loginbean, String email) {
        return accountdao.modifyEmail(email, loginbean);
    }

    public AccountBean login(AccountBean bean) {
        AccountBean loginBean = accountdao.login(bean);
//        return loginBean;
        if (loginBean.getU_pwd() != null) {
            if (loginBean.getU_pwd().equals(bean.getU_pwd())) {
                //密码相同
                return loginBean;
            } else {
                //密码不同
                return null;
            }
        } else {
            //密码为空
            return null;
        }

    }

    public AccountBean register(String u_name, String u_login_name,String u_pwd,String mobile,String email) {

        return accountdao.register(u_name, u_login_name, u_pwd,mobile,email);

    }

}
