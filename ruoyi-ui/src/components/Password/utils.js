export const usePassword = (options) => {
  const defaultRules = [
    {
      key: "length",
      label: "长度在6到20个字符",
      regExp: /^.{10,20}$/
    },
    {
      key: "lowercase",
      label: "使用小写字母",
      regExp: /[a-z]+/
    },
    {
      key: "capital",
      label: "用大写字母",
      regExp: /[A-Z]+/
    },
    {
      key: "number",
      label: "使用数字",
      regExp: /\d{1,}/
    }
  ];
  const ruleItems = options
    ? options.reduce((res, item) => {
      if (typeof item === "string") {
        const rule = defaultRules.find(i => item === i.key);
        if (rule) {
          res.push(rule);
        } else {
          throw new Error("无效的规则");
        }
      } else {
        res.push(item);
      }
      return res;
    }, [])
    : [...defaultRules];
  // 返回默认的匹配规则map 和 校验结果mapList
  return {
    checkList: ruleItems.map(({key, label}) => ({key, label})),
    validate: (value) => {
      // 过滤出符合正则的rule[], map返回key[]
      const validList = ruleItems
        .filter(({regExp, validator}) => {
          if (validator) {
            return validator(value);
          } else if (regExp) {
            return regExp.test(value);
          } else {
            return false;
          }
        })
        .map(({key}) => {
          return key;
        });
      return {
        validList,
        valid: validList.length === ruleItems.length
      };
    }
  };
};
