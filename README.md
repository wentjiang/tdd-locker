# tasking

- given 没满的locker,需要存的包  when 存包 then 生成小票
- given 满了的locker,需要存的包  when 存包 then 不生成小票,提示储物柜已满
- given 已存了包的locker,未使用的正确的小票 when 取包 then 验证通过,拿到包
- given locker,错误的小票 when 取包 then 验证失败,提示票无效,未拿到包
- given locker,已经被验证过的小票 when 取包 then 验证失败,提示票无效,未拿到包

