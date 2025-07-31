import { CardFace } from "./CardFace";
import { ImageUris } from "./ImageUris";
import { Prices } from "./Prices";
import { PurchaseUris } from "./PurchaseUris";
import { Set } from "./Set";

export interface MTGCard {
    id: string,
    oracleId: string,
    name: string,
    releasedAt: Date,
    imageUris: ImageUris,
    manaCost: string,
    cmc: string,
    typeLine: string,
    oracleText: string,
    power: string,
    toughness: string,
    colors: string[],
    colorIdentity: string[],
    keywords: string[],
    rarity: string,
    set: string,
    setId: string,
    setName: string,
    prices: Prices,
    purchaseUris: PurchaseUris
    cardFaces: CardFace[],
    setDTO: Set,
    copies: number,
    foil: boolean
}