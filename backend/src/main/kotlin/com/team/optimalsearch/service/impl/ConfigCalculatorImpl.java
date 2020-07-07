package com.team.optimalsearch.service.impl;

import com.team.optimalsearch.service.ConfigCalculatorService;

public class ConfigCalculatorImpl implements ConfigCalculatorService {

    long number_nodes,
            d_max,
            child_bisection;

    long[]number = new long[10];

    float a;

    long node_order, r;

    double max, min;

    double[][] A = new double[100][10];

    String str;
    boolean flag_pair_value;

    //void start_calc(long int, int),
    //calc_metrik(__int64, int),
    //find_sub_system(long int, int);
    //void find_sub_upper(long int, int);  //Методы отличаются друг от друга!
    int conf;

    //Набір констант
    final int N = 1, d = 2, I = 3, D = 4, B = 5, W = 6, P = 7;

    void start_calc(long count, int order) {

        r = order / 2;//высчитуем радиус системы

        a = (float) Math.pow(count,1f / r);//количество узлов в одном измерении

        d_max = (long) a; //сохраним значение в инт(обрежим дробную часть если она имеется)

        a = a - d_max;// высчитуем разницу между флоатом и интом
        if (a == 0) {// если разницы нету то это идельный гиперкуб
            for (int i = 0; i < r; i++)
                number[i] = d_max;// запоминаем и выводим
        } else// иначе переходим ко второй части, находим оптимальную структуру
        {
            a = count;
            for (int i = 0, b = (int) count; i < r; i++) {
                a = (float) Math.pow(b, (float) 1 / (r - i));//находим количесвто узлов в каждом измерении(при акждом проходе цыкла отнимаем 1 найденое раниие измерение)
                max = 2;//устанавливаем минимальное количесвто узлов 2 для измерения
                for (; max < a; )// цыкл для нахождения двух самых близких чисел из ряда чисел 2^N, одно снизу другое сверху
                {
                    min = max;   //запоминаем нижнюю границу
                    max = max * 2; //выпоняем каждый раз умножение на 2, находим верхнюю
                }
                number[i] = (long) min;// временно сохраняем результат, и предпологаем что они уже выбраны правильно
                number[i + 1] = (long) max;
                min = a - min;// находим растояние от нижнего числа к найденному рание числу а
                max = max - a;// находим растояние от верхнего числа к найденному рание числу а
                if (min > max)//сравниваем растояние, и определяем какое короче
                    number[i] = number[i + 1];//устанавливаем найденное значение на 1 метосто, 2-е и т.д.
                b = (int) (b / number[i]);//отнимаем от количесвта узлов найденые уже
                number[i + 1] = b;//предпологаем что это последний круг цыкла
            }
        }
        if (flag_pair_value == false) {
            number[(int) r] = 2;
            number_nodes = number_nodes * 2;
            node_order++;
            r++;
        }
        //цыкл который упорядочит значения в масиве
        for (int i = 1; i < r; i++) {
            max = number[i - 1];
            if (max > number[i]) {
                number[i - 1] = number[i];
                number[i] = (long) max;
                i = 0;
            }
        }
    }

    int value = 0; //для сохранения апар витр для заданой конф

    void find_sub_system(long num, int order) {
        for (int i = 0; ; i++) {
            order--;
            if (order > 3)    //только если порядок 4 и больше
            {   //---------------------------------
                a = (float) order / 2;
                if (a != (int) a) {                                  //проверка на парность
                    order--;
                    num = num / 2;
                    flag_pair_value = false;
                } else {
                    flag_pair_value = true;
                }//--------------------------------

                start_calc(num, order);

                a = number[0];

                for (int i1 = 1; i1 < r; i1++) {             //расчет пааметров
                    if (number[i1] > a) {
                        a = number[i1];
                    }
                }
                //---------------------------------
                if (flag_pair_value) {
                    d_max = (long) (2 * num / a);         //домножаем на 2 и сравниваем с чайлд бисекшин
                }                            //если столько же - оптимальная
                else {
                    d_max = (long) (4 * num / a);        //для непарных - на 4 - тоже самое
                }
                //---------------------------------
                if (d_max == child_bisection)  //если попало под половину полной бисекции
                {
                    //str = IntToStr(number[0]);
                    //for (int i = 1; i < r; i++)
                    //    str = str + "*" + IntToStr(number[i]);
                    //Form2 -> Memo1 -> Lines -> Add("");

                    if (!flag_pair_value) {
                        order++;
                        num = num * 2;
                    }

                    //Form2 -> Memo1 -> Lines -> Add("Під оптимальний варіант конфігурації мережі з порядком вузла d = " + IntToStr(order) + " і розмірами - " + str + " вузлів");
                    calc_metrik((int) num, order);
                } else {
                    if (i == 0) {
                        //Form2 -> Memo1 -> Lines -> Add("Під оптимальні варіанти не знайдені.");
                    }
                    break;
                }

            } else {
                if (i == 0) {
                    //Form2 -> Memo1 -> Lines -> Add("Під оптимальні варіанти не знайдені");
                }
                break;
            }
        }
    }

    void calc_metrik(int num, int order) {
        if (conf == 0) {

//            if (ComboBox1 -> ItemIndex == 3)
//                addCell(0, 1, "Гіперкуб");
//            else
//                addCell(0, 1, "Задана");
//
//            addCell(1, 0, "N");
//            addCell(2, 0, "d");
//            addCell(3, 0, "I");
//            addCell(4, 0, "D");
//            addCell(5, 0, "B");
//            addCell(6, 0, "W");
//            addCell(7, 0, "P");

            conf++;
            //StringGrid1 -> RowCount += 1;
        } else {
            //addCell(0, conf + 1, "O" + IntToStr(conf));
            conf++;
            //StringGrid1 -> RowCount += 1;
        }


        //Розраховуємо кількість зв'язків
        d_max = num * order / 2;

        //Memo1 -> Lines -> Add("Кількість зв'язків (топологічна вартість) I = " + IntToStr(d_max));

        //addCell(N, conf, IntToStr(num));   //кі-сть  вузлів
        //addCell(d, conf, IntToStr(order)); //порядок вузлів
        //addCell(I, conf, IntToStr(d_max)); //кі-сть зв'язків

        //Розраховуємо максимальний діаметр
        d_max = 0;
        for (int i = 0; i < r; i++)
            d_max = d_max + number[i] / 2;

        //Memo1 -> Lines -> Add("Максимальний діаметр (максимальна затримка передачі повідомлень між найбільш віддаленими вузлами) D = " + IntToStr(d_max));
        //addCell(D, conf, IntToStr(d_max));

        //Розраховуємо ширину бісекції
        a = number[0];
        for (int i = 1; i < r; i++)
            if (number[i] > a)
                a = number[i];
        d_max = (long) (2 * num / a);

        //Memo1 -> Lines -> Add("Ширина бісекції (мінімальна кількість зв'язків між двома половинами мережі) B = " + IntToStr(d_max));
        //addCell(B, conf, d_max);

        //Розраховуємо апаратурні витрати
        d_max = num * ((7 * order) - 3);

        //Memo1 -> Lines -> Add("Апаратурні витрати (логічні елементи) I = " + IntToStr(d_max));

        //addCell(W, conf, IntToStr(d_max));

        if (conf == 1) { // Для заданої мережі
            value = (int) d_max;
            //addCell(P, conf, "100%");
        } else {          //усі наступні
            float proc = ((float) (d_max) / (float) (value)) * 100;
            //addCell(P, conf, IntToStr((int) proc) + "%");

            //TODO флоат
        }
    }

    boolean upper_optim = false;

    void Button1Click() {
        conf = 0;

        number_nodes = 0; //количество узлов
        node_order = 0; //порядок узла

//TODO bring to front---------------------------------------------------------------------
//            if (number_nodes > pow(2, 20) || node_order < 4 || node_order > 20) {   // проверка на граничные параметры
//                ShowMessage("Введені вами значення знаходяться поза діапазоном дозволених чисел. Переконайтесь, що введені вами числа виконують дані умови: кількість вузлів не більше числа 2^20, порядок вузла не перевищуе число 20 та не нижче числа 4");
//                return;
//            }
//
//            //прверка на мин значение количесвта узлов при заданом порядке
//            if (number_nodes < pow(4, (node_order / 2))) {
//                ShowMessage("При заданих розмірностях мережі вона не має сенсу! Вам потрібно збільшити кількість вузлів або зменшити їх порядок.");
//                return;
//            }
//        } else if (number_nodes > pow(2, 20)) {
//            // проверка на граничные параметры
//            ShowMessage("Введені вами значення знаходяться поза діапазоном дозволених чисел. Переконайтесь, що введені вами числа виконують дані умови: кількість вузлів не більше числа 2^20, порядок вузла не перевищуе число 20 та не нижче числа 4");
//            return;
//        }
//
//
//        a = number_nodes;  //проверка на пренадлежность к ряду чисел 2^N
//        int count = 0;
//        for (; a >= 2; ) {        // более єлегантный вариант - a && !(a & (a - 1));
//            a = a / 2;        // но нам нужен каунт
//            count++;
//        }
//        if (a != 1) {
//            for (; max < number_nodes; ) {
//                min = max;
//                max = max * 2;
//            }
//            CheckBox1 -> Visible = true;
//            CheckBox2 -> Visible = true;
//
//            CheckBox1 -> Caption = min;
//            CheckBox2 -> Caption = max;
//
//            ShowMessage("Рекомендується розмір мережі N=2^n! Виберіть одне із двох запропонованчих варіантів.");
//            return;
//        } else {
//            CheckBox1 -> Checked = false;
//            CheckBox2 -> Checked = false;
//        }
//TODO bring to front---------------------------------------------------------------------
//
//        if (ComboBox1 -> ItemIndex == 3) {   //усі підоптимальні
//            node_order = count;
//        }
//
//        //граничный размер при котором мережа не есть подоптимальной
//        child_bisection = number_nodes / 4;
//        //----проверка порядка узла на парность
//        a = (float) node_order / 2;
//        if (a != (int) a) {
//            node_order--;
//            number_nodes = number_nodes / 2;
//            flag_pair_value = false;
//        } else {
//            flag_pair_value = true;
//        }//------------------------------------
//
//        if (flag_pair_value) {
//            if (count != node_order)
//                Form2 -> Memo1 -> Lines -> Add("Мережа має гіперкубічну конфігурацію при порядку вузла d = " + IntToStr(count));
//            else
//                Form2 -> Memo1 -> Lines -> Add("Мережа має гіперкубічну конфігурацію d = " + IntToStr(count));
//        } else {
//            if (count != node_order + 1)
//                Form2 -> Memo1 -> Lines -> Add("Мережа має гіперкубічну конфігурацію при порядку вузла d = " + IntToStr(count));
//            else
//                Form2 -> Memo1 -> Lines -> Add("Мережа має гіперкубічну конфігурацію d = " + IntToStr(count));
//
//        }
//
//        //начало расчетов (расчет - number[], r, a)
//        start_calc(number_nodes, node_order);  //если парное то передаем значения as is
//        //если нет - то на 1 порядок меньше
//        //формируем строку с конфиг сети
//        str = IntToStr(number[0]);
//        for (int i = 1; i < r; i++)
//            str = str + "*" + IntToStr(number[i]);
//
//        if (ComboBox1 -> ItemIndex == 0 || ComboBox1 -> ItemIndex == 2 || ComboBox1 -> ItemIndex == 1) {
//            //задана мережа або підоптимальні знизу
//            Memo1 -> Lines -> Add("");
//            Memo1 -> Lines -> Add("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
//            Memo1 -> Lines -> Add("Вхідні параметри - N = " + Form2 -> Edit1 -> Text + " d = " + Form2 -> Edit2 -> Text);
//
//            Memo1 -> Lines -> Add("Знайдена конфігурація мережі - " + str + " вузлів");
//            calc_metrik(number_nodes, node_order);  //выводим значения
//
//        }
//
//        if (ComboBox1 -> ItemIndex == 1) {
//            find_sub_upper(number_nodes, node_order);
//        }
//        if (ComboBox1 -> ItemIndex == 2) {
//            find_sub_system(number_nodes, node_order);
//        }
//        if (ComboBox1 -> ItemIndex == 3) {
//
//            Memo1 -> Lines -> Add("");
//            Memo1 -> Lines -> Add("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
//            Memo1 -> Lines -> Add("Вхідні параметри - N = " + Form2 -> Edit1 -> Text);
//
//            Memo1 -> Lines -> Add("Гіперкубічна конфігурація мережі - " + str + " вузлів");
//            calc_metrik(number_nodes, node_order);  //выводим значения
//
//            find_sub_system(number_nodes, node_order);
//        }

    }

    void find_sub_upper(long num, int order) {
//        for (int i = 0, c = 0; ; i++) {
//            order++;
//            if (order < 20)    //только если порядок 20 и меньше
//            {   //---------------------------------
//                a = (float) order / 2;
//                if (a != (int) a) {                                  //проерка на парность
//                    order--;
//                    num = num / 2;
//                    flag_pair_value = false;
//                } else {
//                    flag_pair_value = true;
//                }//--------------------------------
//                start_calc(num, order);
//                a = number[0];
//                for (int i = 1; i < r; i++)             //расчет пааметров
//                    if (number[i] > a)
//                        a = number[i];
//                //---------------------------------
//                if (flag_pair_value) {
//                    d_max = 2 * num / a;         //домножаем на 2 и сравниваем с чайлд бисекшин
//                }                            //если столько же - оптимальная
//                else {
//                    d_max = 4 * num / a;        //для непарных - на 4 - тоже самое
//                }
//
//                if (d_max <= child_bisection || d_max == child_bisection * 2)  //если попало под половину полной бисекции
//                {
//                    if (d_max == child_bisection * 2)
//                        c++;
//                    if (c == 2) {
//                        break;
//                    }
//                    str = IntToStr(number[0]);
//                    for (int i = 1; i < r; i++)
//                        str = str + "*" + IntToStr(number[i]);
//                    Form2 -> Memo1 -> Lines -> Add("");
//                    if (!flag_pair_value) {
//                        order++;
//                        num = num * 2;
//                    }
//                    Form2 -> Memo1 -> Lines -> Add("Під оптимальний варіант конфігурації мережі з порядком вузла d = " + IntToStr(order) + " і розмірами - " + str + " вузлів");
//
//                    if (d_max >= child_bisection)
//                        calc_metrik(num, order);
//                } else if (i == 0) {
//                    Form2 -> Memo1 -> Lines -> Add("Під оптимальні варіанти не знайдені.");
//                    break;
//                }
//
//            } else {
//                if (i == 0) {
//                    Form2 -> Memo1 -> Lines -> Add("Під оптимальні варіанти не знайдені");
//                }
//                break;
//            }
//        }

    }


}
