//package main.models;
//
//
//import market.importer.OrderProductType;
//import market.importer.OrderType;
//import market.importer.ProductType;
//import market.importer.UserType;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//
///**
// * Created by Троицкий Дмитрий on 14.04.2017.
// */
//public class DataBaseConnector {
//
//    /** Примитивное создание коннекта */
//    public static Connection initConnection() {
//
//        Connection connection = null;
//
//        try {
//            Class.forName( "com.mysql.jdbc.Driver" );
//        } catch(ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection( "jdbc:mysql://sleitor.stc5.ru/demo", "demo", "q1w2e3r4" );
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//        return connection;
//    }
//
//    /** Создание таблиц в БД */
//    public static void createTable() {
//
//        Connection db = initConnection();
//        try {
//            Statement statement = db.createStatement();
//            //            statement.execute( "CREATE TABLE users(id serial, uuid uuid, userName CHAR(255), email CHAR(255), firstName CHAR(255), secondName CHAR(255), lastName CHAR(255), address CHAR(255), CONSTRAINT pkey PRIMARY KEY(id) ); " );
//            //            statement.execute( "CREATE TABLE products( id serial, name CHAR(255), description CHAR(255), quantity integer, CONSTRAINT pkey PRIMARY KEY(id) ); " );
//
//
//            statement.execute( "CREATE TABLE users( uuid CHAR(36) NOT NULL, userName CHAR(255), email CHAR(255), firstName CHAR(255), secondName CHAR(255), lastName CHAR(255), address CHAR(255), PRIMARY KEY(uuid) );" );
//            statement.execute( "CREATE TABLE products( uuid CHAR(36) NOT NULL, name CHAR(255), description CHAR(255), quantity INTEGER, cost INTEGER, PRIMARY KEY(uuid) );" );
//            statement.execute( "CREATE TABLE orders (uuid CHAR(36) NOT NULL, uuid_user CHAR(36), date DATE, cost FLOAT, PRIMARY KEY (uuid), FOREIGN KEY (uuid_user) REFERENCES users(uuid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION);" );
//            statement.execute( "CREATE TABLE orderProducts( uuid CHAR(36) NOT NULL, uuid_order CHAR(36) NOT NULL, uuid_product CHAR(36) NOT NULL, count INTEGER, cost FLOAT, PRIMARY KEY(uuid), CONSTRAINT fk_orderProducts_products FOREIGN KEY (uuid_product) REFERENCES products(uuid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT fk_orderProducts_order FOREIGN KEY (uuid_order) REFERENCES orders(uuid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION );" );
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /** Удаление таблиц в БД */
//    public static void dropTable() {
//
//        Connection db = initConnection();
//        try {
//            Statement statement = db.createStatement();
//            statement.execute( "DROP TABLE orderProducts, orders, users, products" );
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /** Выгрузка из БД Продуктов */
//    public static ArrayList<ProductType> getProducts() {
//
//        Connection db = initConnection();
//        try {
//            Statement statement = db.createStatement();
//            ResultSet resultSet = statement.executeQuery( "SELECT * FROM products" );
//
//            ArrayList<ProductType> data = new ArrayList<>();
//
//            while (resultSet.next()) {
//                ProductType product = new ProductType();
//                product.setUuid( resultSet.getString( 1 ) );
//                product.setName( resultSet.getString( 2 ) );
//                product.setDescription( resultSet.getString( 3 ) );
//                product.setQuantity( resultSet.getInt( 4 ) );
//                product.setCost( resultSet.getInt( 5 ) );
//                data.add( product );
//            }
//            return data;
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }
//
//    /** Выгрузка из БД пользователей */
//    public static ArrayList<UserType> getUsers() {
//
//        Connection db = initConnection();
//
//        try {
//
//            Statement statement = db.createStatement();
//            ResultSet resultSet = statement.executeQuery( "SELECT * FROM users" );
//
//            ArrayList<UserType> data = new ArrayList<>();
//
//            while (resultSet.next()) {
//
//                UserType user = new UserType();
//                user.setUuid( resultSet.getString( 1 ) );
//                user.setUserName( resultSet.getString( 2 ) );
//                user.setEmail( resultSet.getString( 3 ) );
//                user.setFirstName( resultSet.getString( 4 ) );
//                user.setSecondName( resultSet.getString( 5 ) );
//                user.setLastName( resultSet.getString( 6 ) );
//                user.setAddress( resultSet.getString( 7 ) );
//                data.add( user );
//            }
//            return data;
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//
//
//    }
//
//    /** Выгрузка из БД заказов */
//    public static ArrayList<OrderType> getOrders() {
//
//        Connection db = initConnection();
//
//        try {
//
//            Statement statement = db.createStatement();
//            ResultSet resultSet = statement.executeQuery( "SELECT * FROM orders" );
//
//            ArrayList<OrderType> data = new ArrayList<>();
//
//            while (resultSet.next()) {
//
//                OrderType order = new OrderType();
//                order.setUuid( resultSet.getString( 1 ) );
//                //                order.setUser( resultSet.getString( 2 ) );
//                order.setUser( resultSet.getString( 2 ) );
//
//                GregorianCalendar gc = new GregorianCalendar();
//                gc.setTime( resultSet.getDate( 3 ) );
//
//                try {
//                    order.setDate( DatatypeFactory.newInstance().newXMLGregorianCalendar( gc ) );
//
//                } catch(DatatypeConfigurationException e) {
//                    e.printStackTrace();
//                }
//
//                order.setCost( resultSet.getFloat( 4 ) );
//                data.add( order );
//            }
//            return data;
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//
//    }
//
//    /** Выгрузка из БД продуктов в заказах */
//    public static ArrayList<OrderProductType> getOrderProducts() {
//
//        Connection db = initConnection();
//
//        try {
//
//            Statement statement = db.createStatement();
//            ResultSet resultSet = statement.executeQuery( "SELECT * FROM orderProducts" );
//
//            ArrayList<OrderProductType> data = new ArrayList<>();
//
//            while (resultSet.next()) {
//
//                OrderProductType order = new OrderProductType();
//                order.setUuid( resultSet.getString( 1 ) );
//                //                order.setUuidProduct( resultSet.getString( 2 ) );
//                order.setOrder( resultSet.getString( 2 ) );
//                order.setProduct( resultSet.getString( 3 ) );
//                order.setCount( resultSet.getInt( 4 ) );
//                order.setCost( resultSet.getFloat( 5 ) );
//                data.add( order );
//            }
//            return data;
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        //
//        //        INSERT INTO `users` (`uuid`, `userName`, `email`, `firstName`, `secondName`, `lastName`, `address`) VALUES
//        //                ('a0aafa4b-2447-11e7-9260-4663796b7078', 'vasya', 'vasya@yandex.ru', 'Василий', 'Петрович', 'Исаков', 'ввв ленингад эксбб точка ру');
//        //
//        //
//        //        INSERT INTO `products` (`uuid`, `name`, `description`, `quantity`, `cost`) VALUES
//        //                ('4fb9a0da-2444-11e7-9260-4663796b7078', 'Хлеб', 'Вкусненький, свеженький хлебушек', 50, 35),
//        //                ('b2ccd701-23ec-11e7-9260-4663796b7078', 'Молоко', 'Вкусное, свежее молочко', 25, 80);
//        //
//        //        INSERT INTO `orders`(`uuid`, `uuid_user`, `date`, `cost`) VALUES
//        //                ('5b54e942-2502-11e7-9260-4663796b7078','a0aafa4b-2447-11e7-9260-4663796b7078',2017-03-19,80);
//        //
//        //        INSERT INTO  `orderProducts` (  `uuid` ,  `uuid_order` ,  `uuid_product` ,  `count` ,  `cost` )
//        //        VALUES (
//        //                '688ac6a7-24f7-11e7-9260-4663796b7078',  '5b54e942-2502-11e7-9260-4663796b7078',  'b2ccd701-23ec-11e7-9260-4663796b7078', 1, 80
//        //        );
//        //
//
//
//        return null;
//    }
//
//    /** Загрузка в БД продуктов */
//    public static void setProducts(ArrayList<ProductType> products) {
//
//        Connection db = initConnection();
//
//        try {
//            PreparedStatement statement = db.prepareStatement( "INSERT INTO products (uuid, name, description, quantity, cost) VALUES (?,?,?,?,?)" );
//
//            for (ProductType product : products) {
//                statement.setString( 1, product.getUuid() );
//                statement.setString( 2, product.getName() );
//                statement.setString( 3, product.getDescription() );
//                statement.setFloat( 4, product.getQuantity() );
//                statement.setFloat( 5, product.getCost() );
//                statement.executeUpdate();
//            }
//
//            db.close();
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /** Загрузка в БД пользователей */
//    public static void setUsers(ArrayList<UserType> users) {
//
//        Connection db = initConnection();
//
//        try {
//            PreparedStatement statement = db.prepareStatement( "INSERT INTO users (uuid, userName, email, firstName, secondName, lastName, address) VALUES (?,?,?,?,?,?,?)" );
//
//            for (UserType user : users) {
//                statement.setString( 1, user.getUuid() );
//                statement.setString( 2, user.getUserName() );
//                statement.setString( 3, user.getEmail() );
//                statement.setString( 4, user.getFirstName() );
//                statement.setString( 5, user.getSecondName() );
//                statement.setString( 6, user.getLastName() );
//                statement.setString( 7, user.getAddress() );
//                statement.executeUpdate();
//            }
//
//            db.close();
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /** Выгрузка в БД заказов */
//    public static void setOrders(ArrayList<OrderType> orders) {
//
//        Connection db = initConnection();
//
//        try {
//            PreparedStatement statement = db.prepareStatement( "INSERT INTO orders (uuid, uuid_user, date, cost) VALUES (?,?,?,?)" );
//
//            for (OrderType order : orders) {
//                statement.setString( 1, order.getUuid() );
//                statement.setString( 2, order.getUser() );
//
//                Date sqlDate;
//
//                if (order.getDate() == null) {
//                    sqlDate = new Date((new java.util.Date()).getTime());
//                } else {
//                    sqlDate = new Date(order.getDate().toGregorianCalendar().getTimeInMillis());
//                }
//                statement.setDate(3, sqlDate);
//
//                statement.setFloat( 4, order.getCost() );
//                statement.executeUpdate();
//            }
//
//            db.close();
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /** Выгрузка в БД продуктов в заказах*/
//    public static void setOrderProducts(ArrayList<OrderProductType> orderProducts) {
//
//        Connection db = initConnection();
//
//        try {
//            PreparedStatement statement = db.prepareStatement(
//                    "INSERT INTO orderProducts (uuid, uuid_order, uuid_product, count, cost) VALUES (?,?,?,?,?)" );
//
//            for (OrderProductType orderProduct : orderProducts) {
//                statement.setString( 1, orderProduct.getUuid() );
//                statement.setString( 2, orderProduct.getOrder() );
//                statement.setString( 3, orderProduct.getProduct() );
//                statement.setInt( 4, orderProduct.getCount() );
//                statement.setFloat( 5, orderProduct.getCost() );
//                statement.executeUpdate();
//            }
//
//            db.close();
//
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
////
////        INSERT INTO `users` (`uuid`, `userName`, `email`, `firstName`, `secondName`, `lastName`, `address`) VALUES
////                ('a0aafa4b-2447-11e7-9260-4663796b7078', 'vasya', 'vasya@yandex.ru', 'Василий', 'Петрович', 'Исаков', 'ввв ленингад эксбб точка ру');
////
////
////        INSERT INTO `products` (`uuid`, `name`, `description`, `quantity`, `cost`) VALUES
////                ('4fb9a0da-2444-11e7-9260-4663796b7078', 'Хлеб', 'Вкусненький, свеженький хлебушек', 50, 35),
////                ('b2ccd701-23ec-11e7-9260-4663796b7078', 'Молоко', 'Вкусное, свежее молочко', 25, 80);
////
////        INSERT INTO `orders`(`uuid`, `uuid_user`, `date`, `cost`) VALUES
////                ('5b54e942-2502-11e7-9260-4663796b7078','a0aafa4b-2447-11e7-9260-4663796b7078',2017-03-19,80);
////
////        INSERT INTO  `orderProducts` (  `uuid` ,  `uuid_order` ,  `uuid_product` ,  `count` ,  `cost` )
////        VALUES (
////                '688ac6a7-24f7-11e7-9260-4663796b7078',  '5b54e942-2502-11e7-9260-4663796b7078',  'b2ccd701-23ec-11e7-9260-4663796b7078', 1, 80
////        );
////
