# ITDC-JAVA
Практическая работа №9
======================

## Организация работ

Рекомендуется выделить отдельную директорию для всех практических работ и задач.

### Пример организации директории

```
F:\
|
|--Workspace
|----Practice_01_Task_01
|----Practice_01_Task_02
|----...
|----Practice_02_Task_01
|----...
|----MCCME_Task_2936
|----MCCME_Task_2937
|----...
```

## Ключевые инструменты разработчика Java

* [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Eclipse IDE](https://eclipse.org/downloads/packages/eclipse-ide-java-developers/oxygenr)

### Альтернативная среда разработки (по желанию)

* [IntelliJ IDEA](https://www.jetbrains.com/idea)

## Задание №1: Калькулятор рациональных чисел

Создайте программу, которая помогает пользователю производить арифметику над
рациональными числами. Программа должна позволять производить сложение,
вычитание, умножение и деление. Кроме того, программа должна уметь сравнивать
два рациональных числа.

В вашей программе, пользователь должен ввести первое рациональное число. На
следующей строке пользователь вводит символ или символы оператора (+, -, \*, /, <,
>, =, !=, <=, >=). Завершать ввод должен второй операнд на следующей строке.
Программа должна произвести проверку данных, произвести расчет и напечатать
результат на экран. Программа должна сообщать о некорректном вводе.

Для представления рациональных чисел вам нужно создать класс Rational. Объекты
такого класса должны поддерживать следующие операции:

| Метод                           | Значение                                                                                             |
| ---------------------: | :--------------------------------------------------------------------------------------------------- |
| Rational(int n, int d) | Конструктор, поднимает исключение если знаменатель равен 0                                           |
| add(Rational r)        | Возвращает сумму двух рациональных чисел                                                             |
| subtract(Rational r)   | Возвращает разность двух рациональных чисел                                                          |
| multiply(Rational r)   | Возвращает произведение двух рациональных чисел                                                      |
| divide(Rational r)     | Возвращает результат деления двух рациональных чисел                                                 |
| compareTo(Rational r)  | Возвращает -1 если первое число меньше второго, 0 если оба числа равны, 1 если первое больше второго |
| toString()             | Возвращает строковое представления числа                                                             |
| static parse(String s) | см. сноску                                                                                           |

Статичный метод `parse` пытается превратить строку в рациональное число. Метод
поднимает исключение, если строка не хранит корректное представление
рационального числа.

### Пример ввода и вывода

```java
Калькулятор рациональных чисел
==============================
Введите первое число (Ctrl-Z: для выхода): 1/2
Введите оператор: +
Введите второе число (Ctrl-Z: для выхода): 2/3
Результат: 1/2 + 2/3 = 7/6
Введите первое число (Ctrl-Z: для выхода): one
Неверное рациональное число: one
Введите первое число (Ctrl-Z: для выхода): 1/0
Неверное рациональное число: знаменатель не может быть равен нулю
Введите первое число (Ctrl-Z: для выхода): ^Z
```

## Задание на дом

### Чтение

* Java 8. Полное руководство, Девятое издание, Герберт Шилдт: Глава 3, 6, 7, 10
* Java 8. Руководство для начинающих, Шестое издание, Герберт Шилдт: Глава 4, 5, 6
* По желанию: Introduction to Java Programming. Comprehensive, Десятое издание, Y. Daniel Liang: Глава 6

### MCCME

* Задания с A по U: http://informatics.mccme.ru/mod/statements/view.php?id=2296
* Задания с A по Z: http://informatics.mccme.ru/mod/statements/view.php?id=276
* Задания с A по O: http://informatics.mccme.ru/mod/statements/view.php?id=280
* Задания с A по E: http://informatics.mccme.ru/mod/statements/view.php?id=2587
* Задания с A по H: http://informatics.mccme.ru/mod/statements/view.php?id=26735
* Задания с A по L: http://informatics.mccme.ru/mod/statements/view.php?id=208
* Задания с B по L: http://informatics.mccme.ru/mod/statements/view.php?id=282