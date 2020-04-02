module.exports = {
  theme: {
    extend: {
      transitionDuration: {
        '25': '25ms',
        '50': '50ms',
      },
      scale: {
        '105': '1.05',
      },
      width: {
        'third--1': 'calc(33.33333% - 1rem)',
        'half--1': 'calc(50% - 1rem)',
        '2third--1': 'calc(66.66666% - 1rem)',
        'screen-1': 'calc(100vw - 1rem)',
        'full-1': 'calc(100% - 1rem)',
      },

      colors: {
        'primary': 'var(--bg-background-primary)',
        'secondary': 'var(--bg-background-secondary)',
        'ternary': 'var(--bg-background-ternary)',
  
        'primaryText': 'var(--text-copy-primary)',
        'secondaryText': 'var(--text-copy-secondary)',
        'ternaryText': 'var(--text-copy-ternary)',
      },
      borderColor: {
        'primary': 'var(--bg-background-primary)',
        'secondary': 'var(--bg-background-secondary)',
        'ternary': 'var(--bg-background-ternary)',
      },
    },
  },
  variants: {},
  plugins: [],
}