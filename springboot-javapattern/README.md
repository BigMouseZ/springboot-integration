# patterns
* Project：Java-Patterns 
* describe：设计模式学习笔记

### 设计模式简述

创建型模式,共五种:工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式.

结构型模式,共七种:适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式.

行为型模式,共十一种:策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式.

创建型模式主要用于描述如何创建对象，结构型模式主要用于描述如何实现类或对象的组合，行为型模式主要用于描述类或对象怎样交互以及怎样分配职责
### 设计模式的六大原则
* 开闭原则（Open Close Principle）
	开闭原则就是说对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。所以一句话概括就是：为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

* 里氏代换原则（Liskov Substitution Principle）
	里氏代换原则(Liskov Substitution Principle LSP)面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。 LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。里氏代换原则是对“开-闭”原则的补充。实现“开-闭”原则的关键步骤就是抽象化。而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。—— From Baidu 百科

* 依赖倒转原则（Dependence Inversion Principle）
	这个是开闭原则的基础，具体内容：真对接口编程，依赖于抽象而不依赖于具体。

* 接口隔离原则（Interface Segregation Principle）
	这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。还是一个降低类之间的耦合度的意思，从这儿我们看出，其实设计模式就是一个软件的设计思想，从大型软件架构出发，为了升级和维护方便。所以上文中多次出现：降低依赖，降低耦合。

* 迪米特法则（最少知道原则）（Demeter Principle）
	为什么叫最少知道原则，就是说：一个实体应当尽量少的与其他实体之间发生相互作用，使得系统功能模块相对独立。

* 合成复用原则（Composite Reuse Principle）
	原则是尽量使用合成/聚合的方式，而不是使用继承。  
	rowspan：规定单元格可横跨的行数。横跨几行属性值就写几，如上图中横跨7行，则rowspan=”7”  
    colspan：规定单元格可纵深的列数。
##表1  常用设计模式一览表  
<table  style="width: 1000px;  ">
    <tr>
        <td width=10%  > 类型  </td>
        <td width=10% >模式名称</td>
        <td width=10% > 学习难度</td>
        <td width=10% > 使用频率</td>
    </tr>
    <tr>
        <td rowspan="7">创建型模式  <br> Creational Pattern </td>
    </tr>
    <tr>
        <td>单例模式 <br>  Singleton Pattern  </td>
        <td> <font color=red >★☆☆☆☆</font></td>
        <td> <font color=red >★★★★☆</td>
    </tr>
     <tr>
            <td>简单工厂模式  <br>  Simple Factory Pattern  </td>
            <td> <font color=red > ★★☆☆☆</td>
            <td> <font color=red >★★★☆☆</td>
     </tr>
<tr>
        <td>工厂方法模式 <br>  Factory Method Pattern  </td>
        <td> <font color=red > ★★☆☆☆</td>
        <td> <font color=red >★★★★★</td>
    </tr>
 <tr>
        <td>抽象工厂模式 <br>  Abstract Factory Pattern  </td>
        <td> <font color=red > ★★★★☆</td>
        <td> <font color=red >★★★★★</td>
    </tr>
 <tr>
        <td>原型模式 <br>  Prototype Pattern  </td>
        <td> <font color=red > ★★★☆☆</td>
        <td> <font color=red >★★★☆☆</td>
    </tr>
 <tr>
        <td>建造者模式 <br>  Builder Pattern  </td>
        <td> <font color=red > ★★★★☆</td>
        <td> <font color=red >★★☆☆☆</td>
    </tr>
  <tr>
         <td rowspan="8">结构型模式  <br> Structural Pattern </td>
     </tr>
 <tr>
        <td>适配器模式 <br>  Adapter Pattern  </td>
        <td> <font color=red > ★★☆☆☆</td>
        <td> <font color=red >★★★★☆</td>
    </tr>
 <tr>
         <td> 桥接模式 <br>  Bridge Pattern  </td>
         <td> <font color=red > ★★★☆☆</td>
         <td> <font color=red >★★★☆☆</td>
     </tr>
  <tr>
          <td>组合模式 <br>  Composite Pattern  </td>
          <td> <font color=red > ★★★☆☆</td>
          <td> <font color=red >★★★★☆</td>
      </tr>
  <tr>
          <td>装饰模式 <br>  Decorator Pattern  </td>
          <td> <font color=red > ★★★☆☆</td>
          <td> <font color=red >★★★☆☆</td>
      </tr>
  <tr>
          <td>外观模式 <br>  Façade Pattern  </td>
          <td> <font color=red > ★☆☆☆☆</td>
          <td> <font color=red >★★★★★</td>
      </tr>
  <tr>
           <td>享元模式 <br> Flyweight Pattern  </td>
           <td> <font color=red > ★★★★☆</td>
           <td> <font color=red >★☆☆☆☆</td>
       </tr>
  <tr>
           <td>代理模式 <br>  Proxy Pattern  </td>
           <td> <font color=red > ★★★☆☆</td>
           <td> <font color=red >★★★★☆</td>
       </tr>
  <tr>
           <td rowspan="12">行为型模式 <br>  Behavioral Pattern  </td>
       </tr>
  <tr>
            <td>职责链模式 <br>   Chain of Responsibility Pattern  </td>
            <td> <font color=red > ★★★☆☆</td>
            <td> <font color=red >★★☆☆☆</td>
        </tr>
  <tr>
            <td>命令模式 <br>  Command Pattern  </td>
            <td> <font color=red > ★★★☆☆</td>
            <td> <font color=red > <font color=red >★★★★☆</td>
        </tr>
   <tr>
             <td>解释器模式 <br>   Interpreter Pattern  </td>
             <td> <font color=red > ★★★★★</td>
             <td> <font color=red >★☆☆☆☆</td>
         </tr>
   <tr>
             <td>迭代器模式 <br>  Iterator Pattern  </td>
             <td> <font color=red > ★★★☆☆</td>
             <td> <font color=red >★★★★★</td>
         </tr>
  <tr>
              <td>中介者模式 <br>  Mediator Pattern  </td>
               <td> <font color=red > ★★★☆☆</td>
               <td> <font color=red >★★☆☆☆</td>
          </tr>
  <tr>
              <td>备忘录模式 <br>  Memento Pattern  </td>
               <td> <font color=red > ★★☆☆☆</td>
               <td> <font color=red >★★☆☆☆</td>
          </tr>
 <tr>
             <td>观察者模式 <br>  Observer Pattern  </td>
              <td> <font color=red > ★★★☆☆</td>
              <td> <font color=red >★★★★★</td>
         </tr>
 <tr>
              <td>状态模式 <br>  State Pattern  </td>
              <td> <font color=red > ★★★☆☆</td>
              <td> <font color=red >★★★☆☆</td>
          </tr>
  <tr>
               <td>策略模式 <br>  Strategy Pattern  </td>
               <td> <font color=red > ★☆☆☆☆</td>
               <td> <font color=red >★★★★☆</td>
           </tr>
  <tr>
               <td>模板方法模式 <br>  Template Method Pattern  </td>
               <td> <font color=red > ★★☆☆☆</td>
               <td> <font color=red >★★★☆☆</td>
           </tr>
    <tr>
                  <td>访问者模式 <br> Visitor Pattern  </td>
                  <td> <font color=red > ★★★★☆</td>
                  <td> <font color=red >★☆☆☆☆</td>
              </tr>
              
</table>