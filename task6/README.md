## Валідація бінів + локалізація.

Треба зробити уніфікований механізм валідації бінів. Правила його валідації мають бути прописані в інструкціях, наприклад, `@NotNullOrEmpty @MinLength`. Кожна анотація має власні характеристики (наприклад, MinLength має характеристику значення мінімальної довжини), а також загальні для всіх - повідомлення (ключ з файлів ресурсів), що виникає при спрацьовуванні валідації.
Наприклад, є Клас:

    class Student {
    
      private String id;
    
      @NotNullOrEmpty(message = "error.student.fio.empty")
      private String fio;
    
      private String inn;
    
      @MinLength(length = 10, message = "error.student.password.length")
      private String password;
    
    }

Зробити метод, який валідуватиме довільну сутність на основі анотацій та повертатиме перелік локалізованих помилок. Надати можливість перемикання локалів між EN/UA:

    public List<String> validate(Object o);