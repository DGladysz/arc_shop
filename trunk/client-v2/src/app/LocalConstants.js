const PhoneRegExp = /((^(84|0){1})(2|3|5|7|8|9))+([0-9]{8})$|((^(01|02){1}))+([0-9]{9})$/;

const LocalConst = Object.freeze({
  PhoneRegExp: PhoneRegExp,
});
export default LocalConst;