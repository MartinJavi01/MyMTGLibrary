import { ImageUris } from "./ImageUris";

export interface CardFace {
    name: string,
    manaCost: string,
    typeLine: string,
    oracleText: string,
    colors: string[],
    defense: string,
    power: string,
    toughness: string,
    imageUris: ImageUris
}