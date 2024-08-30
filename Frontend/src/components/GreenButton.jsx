const Button = ({ text }) => {
  return (
    <button
      style={{
        background: '#294122',
        color: 'white',
        fontSize: '14px',
        border: '1px solid #294122',
      }}
    >
      {text}
    </button>
  );
};

export default Button;
