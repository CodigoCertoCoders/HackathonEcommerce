import React from 'react';
import fonts from '../fonts/fonts.module.css';

const InputWithLabel = ({
  onChange,
  inputClass,
  labelClass,
  labelText,
  inputType,
  htmlFor,
}) => {
  return (
    <>
      <label
        className={`${fonts.robotoRegular} ${labelClass}`}
        htmlFor={htmlFor}
      >
        {labelText}
      </label>
      <input
        onChange={onChange}
        className={inputClass}
        id={htmlFor}
        type={inputType}
      ></input>
    </>
  );
};

export default InputWithLabel;
