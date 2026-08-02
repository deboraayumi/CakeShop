package model;

public class Product {


        static int idCounter;

        private int id;
        private String name;
        private double price;
        private int stock;
        private double salePercent;


        public Product(String name, double price, int stock) {
                stockZeroChecker(stock);
                priceZeroChecker(price);
                nameEmptyChecker(name);
                this.id = idCounter++;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.salePercent = 0;
        }

        int getId(){
            return this.id;
        }

    /*-------------------------Name-------------------------*/

        String getName(){
            return this.name;
        }

        void setName(String name) {
            nameEmptyChecker(name);
            this.name = name;
        }
            //throws erro vai só pra quem tem o throw new ou pra tds?
        void nameEmptyChecker(String name) throws IllegalArgumentException {
            if(name.isEmpty()){
                throw new IllegalArgumentException( "Product name must have at least one word");
            }
        }

    /*-------------------------Price-------------------------*/

        double getPrice(){
            return this.price;
        }

        void setPrice(double price) {
            priceZeroChecker(price);
            this.price = price;
        }

        void priceZeroChecker(double price) throws IllegalArgumentException {
            if(price <=0.0){
                throw new IllegalArgumentException( "Invalid Price. Insert a value bigger than zero");
            }
        }


    /*-------------------------Sale-------------------------*/

        double getSalePrice(){
            return this.price * (1 - (this.salePercent / 100));
            
        }



        double getSalePercent(){
            return this.salePercent;
        }

        void setSalePercent(double salePercent){
            this.salePercent = salePercent;
        }



    /*-------------------------Stock-------------------------*/

        int getStock(){
            return this.stock;
        }

        void setStock(int stock){
            this.stock = stock;
        }

        void addStock(int stock){
            this.stock += stock;
        }

        void subtractStock(int stock) {
            stockZeroChecker(stock);
            this.stock -= stock;
        }    

        void stockZeroChecker(int stock) throws IllegalArgumentException {
            if(stock <0){
                throw new IllegalArgumentException("Invalid Stock. Insert a value bigger than zero");
            }
        }


    /*-------------------------Test Print-------------------------*/

        void printData(){
            System.out.printf("Product Name: %s", this.name);
            System.out.printf("Product Price: %f", this.price);
            System.out.printf("Product Stock: %d", this.stock);
            System.out.printf("Product Sale percent: %f", this.salePercent);
        }
    }
