function validParentheses(parens) {
  let parensArr = parens.split("");
  let rightParen = "(";

  let isClosedSwitch = 0;
  parensArr.forEach((element) => {
    if (isClosedSwitch <= 0) {
      if (element === rightParen) {
        isClosedSwitch--;
      } else {
        isClosedSwitch++;
      }
    }
  });
  if (isClosedSwitch === 0) {
    return true;
  } else {
    return false;
  }
}
console.log(validParentheses("())(()"));
