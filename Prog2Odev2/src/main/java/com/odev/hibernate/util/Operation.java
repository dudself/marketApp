/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odev.hibernate.util;

import com.odev.database.entity.ProductInfo;
import com.odev.database.entity.UserInfo;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author did
 */
public class Operation {
    //User Add/Delete/Get
    public void  addUser(String username, String password, String name, String lastName){
        UserInfo instance = new UserInfo(username, name, lastName, password);

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =session.beginTransaction();
            session.save(instance);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public UserInfo getUser (String username){
        Transaction transaction = null;
        String foo = "FROM UserInfo WHERE user = '"+ username + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<UserInfo> user = session.createQuery(foo).list();
            return user.get(0);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    public void deleteUser(String username){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           transaction = session.beginTransaction();
            Object user = session.load(UserInfo.class, username);
            if(user != null){
                session.delete(user);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }
    //Product Add/Delete/Get
    public void  addProduct(String productName, int quantity, float price){
        ProductInfo instance = new ProductInfo(productName, quantity, price);

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction =session.beginTransaction();
            session.save(instance);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public ProductInfo getProduct (int id){
        Transaction transaction = null;
        String foo = "FROM ProductInfo WHERE product_id = "+ id ;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<ProductInfo> product_id = session.createQuery(foo).list();
            return product_id.get(0);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    public void deleteProduct(int id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           transaction = session.beginTransaction();
            Object product_id = session.load(ProductInfo.class, id);
            if(product_id != null){
                session.delete(product_id);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
        
    }
    //Product sayısı güncelleme
    public boolean updateProductCount(int id, String process,int count) {
        Transaction transaction = null;
        ProductInfo pro = new ProductInfo();
        switch (process) {
            case "decrease":
                try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                if (getProduct(id).getQuantity() != 0) {
                    ProductInfo product = session.load(ProductInfo.class, id);
                    product.setQuantity(product.getQuantity() - 1);
                    session.update(product);
                    transaction.commit();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Selected product is out of stock!");
                    return false;
                }
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                 }
                    e.printStackTrace();
                    return false;

                }
                
            case "increase":
                try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                
                    ProductInfo product = session.load(ProductInfo.class, id);
                    product.setQuantity(product.getQuantity() + 1);
                    session.update(product);
                    transaction.commit();
                    return true;
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                }
                    e.printStackTrace();
                    return false;

                }
            case "multiple increase":
                try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                    ProductInfo product = session.load(ProductInfo.class, id);
                    product.setQuantity(product.getQuantity() + count);
                    session.update(product);
                    transaction.commit();
                    return true;
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                }
                    e.printStackTrace();
                    return false;

                }
            default:
                break;
        }
        return false;
    }
    //Product adıyla id çağırma
    public int getIdByProductName(String productName){
        Transaction transaction = null;
        String foo = "FROM  ProductInfo WHERE productName = '" + productName + "'";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ProductInfo> product = session.createQuery(foo).list();
            return product.get(0).getProduct();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }
    public List<ProductInfo> getProductsList(){
        Transaction transaction = null;
        String foo = "FROM  ProductInfo " ;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ProductInfo> product = session.createQuery(foo).list();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    
}
