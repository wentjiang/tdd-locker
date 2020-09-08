# 

# class 1
```
需求描述：储物柜(Locker)可以存包、取包
评分标准：参考Classroom中的评分标准Excel文档
代码库：https://github.com/DQing/Locker
需求澄清总结：
1. 储物柜容量不限制是指容量不固定，但是一定是会存满的
2. 储物柜没有尺寸限制，默认多大的包都能存
3. 硬件系统功能不需要考虑（开门/关门/按钮/停电/没票纸）
4. 存包失败，需要提示用户是因为储物柜满了
5. 取包失败，需要提示用户是因为票据无效
6. 存包的位置是随机，没有顺序
7. 不要脑补需求，及时和PO确认
8. 不考虑并发
```

# tasking 1

- given 没满的locker,需要存的包  when 存包 then 生成小票
- given 满了的locker,需要存的包  when 存包 then 不生成小票,提示储物柜已满
- given 已存了包的locker,未使用的正确的小票 when 取包 then 验证通过,拿到包
- given locker,错误的小票 when 取包 then 验证失败,未拿到包
- given locker,已经被验证过的小票 when 取包 then 验证失败,提示票无效,未拿到包

# class 2

```
需求：作为一个初级储物柜机器人，我能够按储物柜的顺序来存包，也能取包
需求澄清：
1. PrimaryLockerRobot存包是按照locker顺序存包
2. PrimaryLockerRobot在某个Locker内存包的位置是随机的
3. 报错信息和Locker是一致的
4. PrimaryLockerRobot至少管理一个Locker
5. PrimaryLockerRobot会回收取过包的票据
Note：
在Locker的基础上继续完善，不需要重新创建仓库
```
# tasking 2
- given PrimaryLockerRobot管理多个没有存满的Locker  when PrimaryLockerRobot存包 then PrimaryLockerRobot存包到第一个柜子，返回票据
- given PrimaryLockerRobot管理多个没有存满的Locker，第一个Locker存满,第二个Locker没有满 when PrimaryLockerRobot存包 then 成功存入第二个Locker，返回票据
- given PrimaryLockerRobot管理多个存满的Locker when PrimaryLockerRobot存包 then 存包失败，提示储物柜已满

- given PrimaryLockerRobot管理多个Locker，有效小票 when PrimaryLockerRobot取包 then PrimaryLockerRobot正确取回了包
- given PrimaryLockerRobot管理多个Locker，无效小票 when PrimaryLockerRobot取包 then 取包失败，Robot提示无效票据

# class 3

```
需求：作为一个聪明的储物柜机器人，我能够将包存在可用容量最多的储物柜，并可以取出
```

# tasking 3
- given SmartLockerRobot管理多个没有存满的Locker when SmartLockerRobot存包 then 存包成功,返回小票
- given SmartLockerRobot管理两个没有存满的Locker,第二个locker的空余数量多于第一个locker的空余数量 when SmartLockerRobot存包 then 成功存包到第二个Locker,返回小票
- given SmartLockerRobot管理两个没有存满的Locker,第一个和第二个locker的空余容量相同 when SmartLockerRobot存包 then 成功存包到第一个Locker,返回小票
- given SmartLockerRobot管理多个存满的Locker when SmartLockerRobot存包 then 存包失败,提示储物柜已满

- given SmartLockerRobot管理多个Locker,有效小票 when SmartLockerRobot取包 then SmartLockerRobot正确取回了包
- given SmartLockerRobot管理多个Locker,无效小票 when SmartLockerRobot取包 then 取包失败,Robot提示无效票据


# class 4

```
需求：作为储物柜机器人的经理，我要管理多个机器人，我可以让机器人存包，也可以自己存包
需求澄清：
1. LockerRobotManager至少管理一个Robot或者Locker
2. LockerRobotManager会让Robot先存包，然后再自己存包
3. LockerRobotManager管理的Robot/Locker按顺序存包
```

# tasking 4
- given manager管理两个有容量的locker,没有管理robot when manager存包 then 成功存入第一个locker,返回小票
- given manager管理两个locker,第一个locker已满,第二个locker有空间,没有管理robot when manager存包 then 成功存入第二个locker,返回小票
- given manager管理两个已满的locker,没有管理robot when manager存包 then 存包失败,提示储物柜已满
- given manager没有管理locker,管理两个有空间的Robot when manager存包 then 成功存入第一个robot,返回小票
- given manager没有管理locker,管理一个已经满了的Robot和一个有空间的robot when manager存包 then 成功存入第二个robot,返回小票
- given manager没有管理locker,管理两个已经满了的Robot when manager存包 then 存包失败,提示储物柜已满
- given manager管理1个locker和一个robot,都有可用空间 when manager存包 then 包存入robot,返回小票
- given manager管理1个locker和一个robot,locker有可用空间,robot已满 when manager存包 then 包存入locker,返回小票
- given manager管理1个locker和一个robot,locker和robot均已满 when manager存包 then 存包失败,提示储物柜已满

- given manager管理两个locker,没有管理robot,票据有效 when manager取包 then 取包成功
- given manager管理两个locker,没有管理robot,票据无效 when manager取包 then 取包失败,提示无效票据
- given manager没有管理locker,管理了两个robot,票据有效 when manager取包 then 取包成功
- given manager没有管理locker,管理了两个robot,票据无效 when manager取包 then 取包失败,提示无效票据
- given manager管理了一个locker和一个robot,票据有效 when manager取包 then 取包成功
- given manager管理了一个locker和一个robot,票据无效 when manager取包 then 取包失败,提示无效票据