export const getInput = (input: Element | RadioNodeList | null) => {
    const isInput = input instanceof HTMLInputElement;
    if(!isInput || input == null) return null;
    return input;
}