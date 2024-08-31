const WhiteButton = ({ text }) => {
  return (
    <button
      style={{
        background: '#FFFFFF',
        color: '#294122',
        fontSize: '14px',
        border: '1px solid #000000',
      }}
    >
      {text}
    </button>
  );
};

export default WhiteButton;