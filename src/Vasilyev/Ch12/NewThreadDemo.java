package Vasilyev.Ch12;

class NewThreadDemo{
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Начинается выполнение программы");
        // Аргументом конструктору класса Thread передается
        // анонимный объект анонимного класса:
        Thread t=new Thread(new Runnable(){
            public void run(){
                for(int i=1;i<=5;i++){
                    System.out.println("Дочерний поток:\t"+i);
                    try{
                        Thread.sleep(4500);
                    }
                    catch(InterruptedException e){
                        System.out.println("Прерывание дочернего потока");
                    }
                }
            }
        });
        System.out.println("Запускается дочерний поток");
        t.start();
        for(int k=0;k<=5;k++){
            System.out.println("Главный поток:\t"+(char)('A'+k));
            Thread.sleep(2000);
        }
        // Проверка дочернего потока:
        if(t.isAlive()){
            // Ожидание завершения дочернего потока:
            t.join();
        }
        System.out.println("Выполнение программы завершено");
    }
}